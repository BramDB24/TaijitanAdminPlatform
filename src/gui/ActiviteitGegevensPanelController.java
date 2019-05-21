/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.ActiviteitController;
import domein.DTO.ActiviteitDTO;
import domein.DomeinController;
import domein.Gebruiker;
import domein.GebruikerController;
import domein.Observer;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
public class ActiviteitGegevensPanelController extends GridPane implements Observer{
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
    private ListView<String> listLeden;
    @FXML
    private ListView<String> listAanwezigeLeden;
    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonDelete;
    @FXML
    private ComboBox<String> cbStatus;

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
        cbStatus.getItems().addAll("Volzet", "Niet volzet");
        listLeden.setItems(gebruikerController.toonOverzicht());
        listLeden.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listAanwezigeLeden.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    private void annuleer(ActionEvent event) {
    }

    @FXML
    private void slaOp(ActionEvent event) {
        TableOverzichtPanelController op = new TableOverzichtPanelController(mainPanel);
        GebruikerController g = new GebruikerController();
        final Stage scene = new Stage();
        VBox box = new VBox();
        op.setObservableList(g.toonOverzicht());
        box.getChildren().add(op);
        Scene s = new Scene(box, 400, 600); //Bram is een beestje :333
        scene.setScene(s);
        scene.show();
        Stage s2 = (Stage)this.getScene().getWindow();
        s2.close(); 
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
        ActiviteitDTO dto = (ActiviteitDTO)activiteit;
        txtNaam.setText(dto.getNaam());
        dpStartDatum.setValue(dto.getStartDatum());
        dpEindDatum.setValue(dto.getEindDatum());
        txtMaxAantal.setText(Integer.toString(dto.getAantalAanwezigen()));
    }

    @FXML
    private void voegLidToe(ActionEvent event) {
        ObservableList<String> ledenToevoegen = listLeden.getSelectionModel().getSelectedItems();
        //listLeden.getItems().remove(ledenToevoegen);
        listAanwezigeLeden.setItems(ledenToevoegen);
    }

    @FXML
    private void verwijderLid(ActionEvent event) {
        ObservableList<String> ledenVerwijderen = listAanwezigeLeden.getSelectionModel().getSelectedItems();
        //listAanwezigeLeden.getItems().remove(ledenVerwijderen);
        listLeden.setItems(ledenVerwijderen);
    }


}
