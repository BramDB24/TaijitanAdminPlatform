package gui;

import domein.ActiviteitController;
import domein.DomeinController;
import domein.GebruikerController;
import domein.OefeningController;
import domein.OverzichtController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class MainPanelController extends GridPane {

    //dc's
    private GebruikerController gebruikerController;
    private OefeningController oefeningController;
    private OverzichtController overzichtController;
    private ActiviteitController activiteitController;

    //panels
    private OverzichtPanelController overzichtPanel;
    private OverzichttypesPanelController opc;
    private LidGegevensPanelController lidGegevensPanel;
    private ParametersPanelController parametersPanel;
    private TableOverzichtPanelController tableOverzichtPanel;
    private FilterPanelController filterPanel;
    private AddItemButtonPanelController addItemButtonPanel;
    private ActiviteitGegevensPanelController activiteitGegevensPanel;

    @FXML
    private Button leden;
    @FXML
    private Button activiteiten;
    @FXML
    private Button aanwezigheden;

    public MainPanelController(GebruikerController gebruikerController, OefeningController oefeningController, OverzichtController overzichtController, ActiviteitController activiteitController) {
        this.gebruikerController = gebruikerController;
        this.oefeningController = oefeningController;
        this.overzichtController = overzichtController;
        this.activiteitController = activiteitController;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPanel.fxml"));

        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void clearScreen() {
        this.getChildren().removeAll(lidGegevensPanel, parametersPanel, tableOverzichtPanel, opc, overzichtPanel, activiteitGegevensPanel);
    }

    @FXML
    public void toonLedenlijst(ActionEvent event) {
        this.clearScreen();
        ensureTableCreated();
        addFilterPanel(gebruikerController);
        tableOverzichtPanel.setObservableList(gebruikerController.toonOverzicht());
        tableOverzichtPanel.enableListener();

        if (tableOverzichtPanel.getChildren().contains(addItemButtonPanel)) {
            tableOverzichtPanel.getChildren().remove(addItemButtonPanel);
            if (!tableOverzichtPanel.getChildren().contains(addItemButtonPanel)) {
                addItemButtonPanel = new AddItemButtonPanelController(this, gebruikerController);
                tableOverzichtPanel.getChildren().add(addItemButtonPanel);
            }
        } else {
            addItemButtonPanel = new AddItemButtonPanelController(this, gebruikerController);
            tableOverzichtPanel.getChildren().add(addItemButtonPanel);
        }

    }

    public void toonItem(Object object) {
        switch (object.getClass().getSimpleName()) {
            case "Lid": case "Lesgever" : 
                addLidGegevensPanel();
                gebruikerController.addObserver(lidGegevensPanel);
                gebruikerController.toonItem(object);
                break;
            case "Activiteit":
                addActiviteitGegevensPanel();
                activiteitController.addObserver(activiteitGegevensPanel);
                activiteitController.toonItem(object);

        }
    }



    public void toonOverzicht(String keuze, OverzichttypesPanelController scherm) {
        ensureTableCreated();

        switch (keuze) {
            case "Activiteiten":
                overzichtController.toonActiviteitenOverzicht();
                break;
            case "Inschrijvingen":
                overzichtController.toonInschrijvingenOverzicht();
                break;
            case "Aanwezigheden":
                overzichtController.toonAanwezighedenOverzicht();
                break;
            case "Clubkampioenschap":
                overzichtController.toonClubkampioenschapOverzicht();
                break;
            case "Raadplegingen lesmateriaal":
                overzichtController.toonRaadplegingenLesmateriaalOverzicht();
                break;
        }
        if (tableOverzichtPanel.getChildren().contains(addItemButtonPanel)) {
            tableOverzichtPanel.getChildren().remove(addItemButtonPanel);
        }
        tableOverzichtPanel.setObservableList(overzichtController.toonOverzicht());
        tableOverzichtPanel.disableListener();
    }

    @FXML
    private void toonOverzichtenlijst(ActionEvent event) {
        ensureTableCreated();
        addFilterPanel(overzichtController);
        this.clearScreen();
        opc = new OverzichttypesPanelController(this);
        this.add(opc, 1, 0);
        tableOverzichtPanel.disableListener();
    }


    @FXML
    private void beheerActiviteiten(ActionEvent event) {
        this.clearScreen();
        ensureTableCreated();
        addFilterPanel(activiteitController);

        tableOverzichtPanel.setObservableList(activiteitController.toonOverzicht());
        tableOverzichtPanel.enableListener();

        if (tableOverzichtPanel.getChildren().contains(addItemButtonPanel)) {
            tableOverzichtPanel.getChildren().remove(addItemButtonPanel);
            if (!tableOverzichtPanel.getChildren().contains(addItemButtonPanel)) {
                addItemButtonPanel = new AddItemButtonPanelController(this, activiteitController);
                tableOverzichtPanel.getChildren().add(addItemButtonPanel);
            }
        } else {
            addItemButtonPanel = new AddItemButtonPanelController(this, activiteitController);
            tableOverzichtPanel.getChildren().add(addItemButtonPanel);
        }
    }

    public List<String> getFieldNames(Class<?> klasse) {
        List<String> fieldnames = new ArrayList<>();
        switch (klasse.getSimpleName()) {
            case "Lid": case "Lesgever":
                fieldnames.add("gebruikersnaam");
                fieldnames.add("naam");
                fieldnames.add("voornaam");
                fieldnames.add("graad");
                break;
            case "Activiteit":
                fieldnames.add("naam");
                fieldnames.add("startDatum");
                fieldnames.add("eindDatum");
                fieldnames.add("maxAantal");
                break;
            default:
                fieldnames = getAllFields(klasse);

        }
        updateFilterValues(fieldnames);
        return fieldnames;
    }

    public Map<String, String> getMethodNames(Class<?> klasse) {
        Map<String, String> methodnames = new HashMap<>();
        switch (klasse.getSimpleName()) {
            case "Activiteit":
                methodnames.put("getAantalAanwezigen", "Aantal aanwezigen");
                break;
        }
        return methodnames;
    }

    private List<String> getAllFields(Class<?> klasse) {
        List<String> fields = new ArrayList<>();
        if (klasse.getSuperclass() != null) {
            Arrays.asList(klasse.getSuperclass().getDeclaredFields()).stream()
                    .map(field -> field.getName()).collect(Collectors.toList()).forEach(x -> fields.add((String) x));
        }
        Arrays.asList(klasse.getDeclaredFields()).stream()
                .map(field -> field.getName()).collect(Collectors.toList()).forEach(x -> fields.add((String) x));
        return fields;
    }

    private void updateFilterValues(List<String> values) {
        filterPanel.setValues(values);
    }

    private void ensureTableCreated() {
        if (tableOverzichtPanel == null) {
            tableOverzichtPanel = new TableOverzichtPanelController(this);
        }
        if (!this.getChildren().stream().anyMatch(o -> o instanceof TableOverzichtPanelController)) {
            this.add(tableOverzichtPanel, 1, 1);
        }
    }

    private void addFilterPanel(DomeinController dc) {
        while (tableOverzichtPanel.getChildren().stream().anyMatch(o -> o instanceof FilterPanelController)) {
            tableOverzichtPanel.getChildren().remove(filterPanel);
        }
        filterPanel = new FilterPanelController(dc, this);
        tableOverzichtPanel.getChildren().add(0, filterPanel);
    }

    private void addLidGegevensPanel() {
        if (lidGegevensPanel == null) {
            lidGegevensPanel = new LidGegevensPanelController(gebruikerController);
        }
        if (!this.getChildren().stream().anyMatch(o -> o instanceof LidGegevensPanelController)) {
            this.add(lidGegevensPanel, 2, 1);
        }
    }

    private void addActiviteitGegevensPanel() {
        if (activiteitGegevensPanel == null) {
            activiteitGegevensPanel = new ActiviteitGegevensPanelController(gebruikerController, activiteitController, this);
        }
        if (!this.getChildren().stream().anyMatch(o -> o instanceof ActiviteitGegevensPanelController)) {
            this.add(activiteitGegevensPanel, 2, 1);
        }
    }
}
