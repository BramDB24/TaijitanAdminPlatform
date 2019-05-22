/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.Activiteit;
import domein.ActiviteitController;
import domein.DTO.ActiviteitDTO;
import domein.DomeinController;
import domein.Gebruiker;
import domein.GebruikerController;
import domein.Observer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bramd
 */
public class ActiviteitGegevensPanelController extends GridPane implements Observer {

    private GebruikerController gebruikerController;

    private ActiviteitController activiteitController;
    @FXML
    private TextField txtNaam;
    @FXML
    private TextField txtMaxAantal;
    @FXML
    private Button btnAnnuleer;
    @FXML
    private Button btnOpslaan;

    private MainPanelController mainPanel;
    @FXML
    private Button btnVerwijder;
    @FXML
    private DatePicker dpStartDatum;
    @FXML
    private DatePicker dpEindDatum;
    @FXML
    private TextField txtHuidigAantal;
    @FXML
    private ListView<Gebruiker> listLeden;
    @FXML
    private ListView<Gebruiker> listAanwezigeLeden;
    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonDelete;
    private ComboBox<String> cbStatus;

    private ObservableList<Gebruiker> ledenLijst;

    private ObservableList<Gebruiker> aanwezigeLijst;
    @FXML
    private TextField txtStatus;

    public ActiviteitGegevensPanelController(GebruikerController gebruikerController, ActiviteitController activiteitController, MainPanelController mainPanel) {
        this.mainPanel = mainPanel;
        this.gebruikerController = gebruikerController;
        this.activiteitController = activiteitController;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ActiviteitGegevensPanel.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        //cbStatus.getItems().addAll("Volzet", "Niet volzet");

        ArrayList<Gebruiker> aanwezig = new ArrayList();
        aanwezigeLijst = FXCollections.observableArrayList(aanwezig);

        listLeden.setItems(ledenLijst);
        listAanwezigeLeden.setItems(aanwezigeLijst);
        listLeden.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listAanwezigeLeden.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        txtStatus.setDisable(true);
        txtHuidigAantal.setDisable(true);
    }

    @FXML
    private void annuleer(ActionEvent event) {
        activiteitController.notifyObservers();
    }

    @FXML
    private void slaOp(ActionEvent event) {
        ActiviteitDTO dto = new ActiviteitDTO();
        dto.setNaam(txtNaam.getText());
        dto.setStartDatum(dpStartDatum.getValue());
        dto.setEindDatum(dpEindDatum.getValue());
        dto.setMaxAantal(Integer.parseInt(txtMaxAantal.getText()));
        dto.setAantalAanwezigen(Integer.parseInt(txtHuidigAantal.getText()));
        List<Gebruiker> l = new ArrayList<>();
        aanwezigeLijst.forEach(a -> l.add(a));
        activiteitController.pasAanwezigenAan(l);
        activiteitController.editItem(dto);
    }

    @FXML
    private void verwijder(ActionEvent event) {
        ButtonType ja = new ButtonType("Ja", ButtonBar.ButtonData.OK_DONE);
        ButtonType annuleren = new ButtonType("Annuleren", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Verwijderen gebruiker", ja, annuleren);
        alert.setTitle("Gebruiker verwijderen");
        alert.setHeaderText("Ben je zeker dat je " + txtNaam.getText() + " wilt verwijderen?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            activiteitController.removeItem();
        }
    }

    @Override
    public void update(Object activiteit) {
        listAanwezigeLeden.getItems().clear();
        ActiviteitDTO dto = (ActiviteitDTO) activiteit;
        txtNaam.setText(dto.getNaam());
        dpStartDatum.setValue(dto.getStartDatum());
        dpEindDatum.setValue(dto.getEindDatum());
        txtMaxAantal.setText(Integer.toString(dto.getMaxAantal()));
        txtHuidigAantal.setText(Integer.toString(dto.getAantalAanwezigen()));
        String status = checkStatus(dto.getMaxAantal(), dto.getAantalAanwezigen());
        //cbStatus.setValue(status);
        txtStatus.setText(status);
        List<Gebruiker> aanwezigheden = activiteitController.geefAanwezigen();
        ledenLijst = FXCollections.observableArrayList(gebruikerController.toonOverzicht());
        if(aanwezigheden!=null){
            aanwezigheden.forEach(help -> aanwezigeLijst.add(help));
            ledenLijst.removeAll(aanwezigheden);
        }
        listAanwezigeLeden.setItems(aanwezigeLijst);
        listLeden.setItems(ledenLijst);
    }

    public String checkStatus(int max, int huidig) {
        if (huidig < max) {
            return "Niet volzet";
        } else {
            return "Volzet";
        }
    }

    @FXML
    private void voegLidToe(ActionEvent event) {
        ObservableList<Gebruiker> ledenToevoegen = listLeden.getSelectionModel().getSelectedItems();
        int tijdelijk = ledenToevoegen.size() + aanwezigeLijst.size();
        if (tijdelijk > Integer.parseInt(txtMaxAantal.getText())) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Er ging iets mis!");
            alert.setHeaderText("Het maximum aantal deelnemers werd bereikt");
            alert.showAndWait();
        } else {
            aanwezigeLijst.addAll(ledenToevoegen);
            ledenLijst.removeAll(ledenToevoegen);
            listLeden.setItems(ledenLijst);
            listAanwezigeLeden.setItems(aanwezigeLijst);
            listLeden.getSelectionModel().clearSelection();
            txtHuidigAantal.setText(Integer.toString(aanwezigeLijst.size()));
        }
    }

    @FXML
    private void verwijderLid(ActionEvent event) {
        ObservableList<Gebruiker> ledenVerwijderen = listAanwezigeLeden.getSelectionModel().getSelectedItems();
        ledenLijst.addAll(ledenVerwijderen);
        aanwezigeLijst.removeAll(ledenVerwijderen);
        listAanwezigeLeden.setItems(aanwezigeLijst);
        listLeden.setItems(ledenLijst);
        listAanwezigeLeden.getSelectionModel().clearSelection();
        txtHuidigAantal.setText(Integer.toString(aanwezigeLijst.size()));
    }

}
