/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.ActiviteitController;
import domein.DomeinController;
import domein.GebruikerController;

import domein.OefeningController;
import domein.OverzichtController;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Johanna
 */
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
    private AddItemButtonPanelController addItemButtonPanelController;

    @FXML
    private Button leden;
    @FXML
    private Button activiteiten;
    @FXML
    private Button aanwezigheden;
    @FXML
    private Button lesmateriaal;

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
        this.getChildren().removeAll(lidGegevensPanel, parametersPanel, tableOverzichtPanel, opc, overzichtPanel, addItemButtonPanelController);
    }//bram is hot big pp

    @FXML
    public void toonLedenlijst(ActionEvent event) {
        this.clearScreen();
        OverzichtController<Object> oc = new OverzichtController<>();
        if (tableOverzichtPanel == null) {
            tableOverzichtPanel = new TableOverzichtPanelController(this);
        }
        if (!this.getChildren().stream().anyMatch(o -> o instanceof TableOverzichtPanelController)) {
            this.add(tableOverzichtPanel, 1, 1);
        }
        tableOverzichtPanel.setObservableList(gebruikerController.toonOverzicht());
        tableOverzichtPanel.enableListener();

    }

    public void toonItem(Object object) {
        this.getChildren().remove(lidGegevensPanel);
        lidGegevensPanel = new LidGegevensPanelController(gebruikerController);
        gebruikerController.addObserver(lidGegevensPanel);
        gebruikerController.toonItem(object);
        this.add(lidGegevensPanel, 2, 1);
    }

    public void toonOverzicht(String keuze, OverzichttypesPanelController scherm) {
        if (tableOverzichtPanel == null) {
            tableOverzichtPanel = new TableOverzichtPanelController(this);
        }
        if (!this.getChildren().stream().anyMatch(o -> o instanceof TableOverzichtPanelController)) {
            this.add(tableOverzichtPanel, 1, 1);
        }
        if (!tableOverzichtPanel.getChildren().stream().anyMatch(o -> o instanceof FilterPanelController)) {
            filterPanel = new FilterPanelController(overzichtController, this);
            tableOverzichtPanel.getChildren().add(0, filterPanel);
        }

        switch (keuze) {
            case "Activiteiten":
                overzichtController.toonActiviteitenOverzicht();
                break;
            case "Inschrijvingen":
                break;
            case "Aanwezigheden":
                overzichtController.toonAanwezighedenOverzicht(LocalDateTime.of(2019, Month.APRIL, 24, 0, 0));
                break;
            case "Clubkampioenschap":
                overzichtController.toonClubkampioenschapOverzicht();
                break;
            case "Raadplegingen lesmateriaal":
                break;
        }

        tableOverzichtPanel.setObservableList(overzichtController.toonOverzicht());
        tableOverzichtPanel.disableListener();
    }

    @FXML
    private void toonOverzichtenlijst(ActionEvent event) {
        if (tableOverzichtPanel == null) {
            tableOverzichtPanel = new TableOverzichtPanelController(this);
        }
        this.clearScreen();
        opc = new OverzichttypesPanelController(this);
        this.add(opc, 1, 0);
        tableOverzichtPanel.disableListener();
    }

    @FXML
    private void toonMateriaal(ActionEvent event) {
        this.clearScreen();
        overzichtPanel = new OverzichtPanelController(oefeningController, this);
        this.add(overzichtPanel, 1, 1);
    }

    @FXML
    private void beheerActiviteiten(ActionEvent event) {
        this.clearScreen();
        tableOverzichtPanel = new TableOverzichtPanelController(this);
        addItemButtonPanelController = new AddItemButtonPanelController(this);
        tableOverzichtPanel.setObservableList(activiteitController.toonOverzicht());
        tableOverzichtPanel.disableListener();
        this.add(tableOverzichtPanel, 1, 1);
        this.add(addItemButtonPanelController, 1, 1);
    }

    public List<String> getFieldNames(Class<?> klasse) {
        List<String> fieldnames = new ArrayList<>();
        switch (klasse.getSimpleName()) {
            case "Lid":
                fieldnames.add("gebruikersnaam");
                fieldnames.add("naam");
                fieldnames.add("voornaam");
                fieldnames.add("graad");
                break;
            case "ActiviteitDTO":
                fieldnames = getAllFields(klasse);
                break;
            default:
                fieldnames = getAllFields(klasse);
                updateFilterValues(fieldnames);
        }

        return fieldnames;
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
}
