/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.io.IOException;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author bramd
 */
public class LidGegevensPanelController extends GridPane {

    private final DomeinController dc;
    @FXML
    private TextField txtFamilienaam;
    @FXML
    private TextField txtVoornaam;
    @FXML
    private TextField txtGeboortedatum;
    @FXML
    private TextField txtStraat;
    @FXML
    private TextField txtPostcode;
    @FXML
    private TextField txtLand;
    @FXML
    private TextField txtRijksregisternummer;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtTelefoon;
    @FXML
    private TextField txtGeboorteplaats;
    @FXML
    private TextField txtHuisnummer;
    @FXML
    private TextField txtStad;
    @FXML
    private TextField txtNationaliteit;
    @FXML
    private TextField txtEmailOuders;
    @FXML
    private TextField txtGsm;
    @FXML
    private ComboBox<Character> cbGeslacht;
    @FXML
    private Button btnOpslaan;
    @FXML
    private Button btnAnnuleer;

    public LidGegevensPanelController(DomeinController dc){
        this.dc = dc;
        System.out.println(getClass().getResource("LidGegevensPanel.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LidGegevensPanel.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        cbGeslacht.setPromptText("M");
        cbGeslacht.getItems().addAll('M', 'V', 'O');
    }  

    @FXML
    private void slaOp(ActionEvent event) {
        try{ //betere errorhandling nodig                                       //nog datepicker ofzo implementeren
        dc.addGebruiker(txtFamilienaam.getText(), txtVoornaam.getText(), "", new Date(), txtStraat.getText(), Integer.parseInt(txtPostcode.getText()),
        txtLand.getText(), Integer.parseInt(txtRijksregisternummer.getText()), txtEmail.getText(),txtTelefoon.getText(), txtGeboorteplaats.getText(),
        Integer.parseInt(txtHuisnummer.getText()), txtStad.getText(), txtNationaliteit.getText(), txtEmailOuders.getText(), txtGsm.getText(), cbGeslacht.getValue());
        } catch (NumberFormatException exception) {
            new Alert(Alert.AlertType.ERROR, "Geen geldig getal").showAndWait();
        } catch (IllegalArgumentException exception) {
            new Alert(Alert.AlertType.ERROR, exception.getMessage()).showAndWait();
        }
    }

    @FXML
    private void annuleer(ActionEvent event) {
        //TODO (nog geen ander scherm atm)
    }
    
}
