/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DTO.GebruikerDTO;
import domein.DomeinController;
import domein.Gebruiker;
import domein.Observer;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author bramd
 */
public class LidGegevensPanelController extends GridPane implements Observer{

    private final DomeinController dc;
    @FXML
    private TextField txtFamilienaam;
    @FXML
    private TextField txtVoornaam;
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
    private TextField txtGraad;
    @FXML
    private Button btnVerwijder;
    @FXML
    private DatePicker dateGeboorte;
    @FXML
    private DatePicker dateInschrijving;

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
            /*String gebruikersnaam = txtFamilienaam.getText() + txtVoornaam.getText();
            if (dc.getGebruikerNamen().stream().anyMatch(naam -> naam == gebruikersnaam)) {
                dc.aanpassenGebruiker(gebruikersnaam, txtFamilienaam.getText(), txtVoornaam.getText(), dateGeboorte.getValue(), txtStraat.getText(), Integer.parseInt(txtPostcode.getText()),
                        txtLand.getText(), txtRijksregisternummer.getText(), txtEmail.getText(), txtTelefoon.getText(), txtGeboorteplaats.getText(),
                        Integer.parseInt(txtHuisnummer.getText()), txtStad.getText(), txtNationaliteit.getText(), txtEmailOuders.getText(), txtGsm.getText(), cbGeslacht.getValue(), Integer.parseInt(txtGraad.getText()), dateInschrijving.getValue());
            } else {
                dc.addGebruiker(txtFamilienaam.getText(), txtVoornaam.getText(), dateGeboorte.getValue(), txtStraat.getText(), Integer.parseInt(txtPostcode.getText()),
                        txtLand.getText(), txtRijksregisternummer.getText(), txtEmail.getText(), txtTelefoon.getText(), txtGeboorteplaats.getText(),
                        Integer.parseInt(txtHuisnummer.getText()), txtStad.getText(), txtNationaliteit.getText(), txtEmailOuders.getText(), txtGsm.getText(), cbGeslacht.getValue(), Integer.parseInt(txtGraad.getText()), dateInschrijving.getValue());
            }
*/
        } catch (NumberFormatException exception) {
            new Alert(Alert.AlertType.ERROR, "Geen geldig getal").showAndWait();
        } catch (IllegalArgumentException exception) {
            new Alert(Alert.AlertType.ERROR, exception.getMessage()).showAndWait();
        }
    }


    public void update(String gebruikerInfo) {
        String[] gebruiker = gebruikerInfo.split(",");
        txtFamilienaam.setText(gebruiker[0]);
        txtVoornaam.setText(gebruiker[1]);
        txtTelefoon.setText(gebruiker[2]);
        dateGeboorte.setValue(LocalDate.parse(gebruiker[3]));
        txtEmail.setText(gebruiker[4]);
        txtGraad.setText(gebruiker[5]);
        dateInschrijving.setValue(LocalDate.parse(gebruiker[6]));
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

    @FXML
    private void verwijder(ActionEvent event) {
        
    }

    @Override
    public void update(Object gebruiker) { //DTO hier?
        GebruikerDTO gebruikerDTO = (GebruikerDTO)gebruiker;
        txtFamilienaam.setText(gebruikerDTO.getNaam());
        txtVoornaam.setText(gebruikerDTO.getVoornaam());
        txtTelefoon.setText(gebruikerDTO.getTelefoonnummer());
        dateGeboorte.setValue(gebruikerDTO.getGeboortedatum());
        txtEmail.setText(gebruikerDTO.getEmail());
        txtGraad.setText(Integer.toString(gebruikerDTO.getGraad()));
        dateInschrijving.setValue(gebruikerDTO.getInschrijvingsdatum());
        txtStraat.setText(gebruikerDTO.getStraat());
        txtHuisnummer.setText(gebruikerDTO.getHuisnummer());
        txtPostcode.setText(gebruikerDTO.getPostcode());
        txtStad.setText(gebruikerDTO.getStad());
        txtLand.setText(gebruikerDTO.getLand());
        txtRijksregisternummer.setText(gebruikerDTO.getRijksregisternummer());
        txtGsm.setText(gebruikerDTO.getGsm());
        txtEmailOuders.setText(gebruikerDTO.getEmailouders());
        txtGeboorteplaats.setText(gebruikerDTO.getGeboorteplek());
        txtNationaliteit.setText(gebruikerDTO.getNationaliteit());
        cbGeslacht.setPromptText(gebruikerDTO.getGeslacht());
    }

}
