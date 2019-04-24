/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import domein.Gebruiker;
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
    @FXML
    private TextField txtGebruikersnaam;
    @FXML
    private TextField txtWachtwoord;
    @FXML
    private TextField txtWachtwoord2;
    @FXML
    private TextField txtGraad;
    @FXML
    private TextField txtInschrijving;

    public LidGegevensPanelController(DomeinController dc) {
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
        try { //betere errorhandling nodig                                       //nog datepicker ofzo implementeren
            if (dc.getGebruikerNamen().stream().anyMatch(naam -> naam == txtGebruikersnaam.getText())) {
                dc.aanpassenGebruiker(txtGebruikersnaam.getText(), txtFamilienaam.getText(), txtVoornaam.getText(), txtWachtwoord.getText(), new Date(), txtStraat.getText(), Integer.parseInt(txtPostcode.getText()),
                        txtLand.getText(), txtRijksregisternummer.getText(), txtEmail.getText(), txtTelefoon.getText(), txtGeboorteplaats.getText(),
                        Integer.parseInt(txtHuisnummer.getText()), txtStad.getText(), txtNationaliteit.getText(), txtEmailOuders.getText(), txtGsm.getText(), cbGeslacht.getValue());
            } else {
                dc.addGebruiker(txtFamilienaam.getText(), txtVoornaam.getText(), "", new Date(), txtStraat.getText(), Integer.parseInt(txtPostcode.getText()),
                        txtLand.getText(), txtRijksregisternummer.getText(), txtEmail.getText(), txtTelefoon.getText(), txtGeboorteplaats.getText(),
                        Integer.parseInt(txtHuisnummer.getText()), txtStad.getText(), txtNationaliteit.getText(), txtEmailOuders.getText(), txtGsm.getText(), cbGeslacht.getValue());
            }
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

    public void update(String gebruikerInfo) {
        String[] gebruiker = gebruikerInfo.split(",");
        txtFamilienaam.setText(gebruiker[0]);
        txtVoornaam.setText(gebruiker[1]);
        txtTelefoon.setText(gebruiker[2]);
        txtGeboortedatum.setText(gebruiker[3]);
        txtEmail.setText(gebruiker[4]);
        //Graad moet er nog bij
        //Inschrijvingsdatum erbij of niet?
        txtStraat.setText(gebruiker[7]);
        txtHuisnummer.setText(gebruiker[8]);
        txtPostcode.setText(gebruiker[9]);
        txtStad.setText(gebruiker[10]);
        txtLand.setText(gebruiker[11]);
        txtRijksregisternummer.setText(gebruiker[12]);
        txtGsm.setText(gebruiker[13]);
        txtEmailOuders.setText(gebruiker[14]);
        txtGeboorteplaats.setText(gebruiker[15]);
        txtNationaliteit.setText(gebruiker[16]);
        cbGeslacht.setPromptText(gebruiker[17]);
    }

}
