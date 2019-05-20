/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DTO.GebruikerDTO;
import domein.DomeinController;
import domein.Observer;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
    private ComboBox<String> cbGeslacht;
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
    @FXML
    private TextField txtGebruikersnaam;
    @FXML
    private Label lblWachtwoord;
    @FXML
    private TextField txtWachtwoord;

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
        //cbGeslacht.setPromptText("M");
        cbGeslacht.getItems().addAll("Man", "Vrouw", "Onbepaald");
    }

    @FXML
    private void slaOp(ActionEvent event) {
        try { //betere errorhandling nodig                                       //nog datepicker ofzo implementeren
            GebruikerDTO dto = new GebruikerDTO();
            dto.setGebruikersnaam(txtGebruikersnaam.getText());
            dto.setNaam(txtFamilienaam.getText());
            dto.setVoornaam(txtVoornaam.getText());
            dto.setGeboortedatum(dateGeboorte.getValue());
            dto.setStraat(txtStraat.getText());
            dto.setPostcode(txtPostcode.getText());
            dto.setLand(txtLand.getText());
            dto.setRijksregisternummer(txtRijksregisternummer.getText());
            dto.setEmail(txtEmail.getText());
            dto.setTelefoonnummer(txtTelefoon.getText());
            dto.setGeboorteplek(txtGeboorteplaats.getText());
            dto.setHuisnummer(txtHuisnummer.getText());
            dto.setStad(txtStad.getText());
            dto.setNationaliteit(txtNationaliteit.getText());
            dto.setEmailouders(txtEmailOuders.getText());
            dto.setGsm(txtGsm.getText());
            dto.setGeslacht(cbGeslacht.getValue()); ///char of string?
            dto.setGraad(Integer.parseInt(txtGraad.getText()));
            dto.setInschrijvingsdatum(dateInschrijving.getValue());
            dto.setWachtwoord(txtWachtwoord.getText());
            dc.editItem(dto);
        } catch (NumberFormatException exception) {
            new Alert(Alert.AlertType.ERROR, "Geen geldig getal").showAndWait();
        } catch (IllegalArgumentException exception) {
            new Alert(Alert.AlertType.ERROR, exception.getMessage()).showAndWait();
        }
    }

    @FXML
    private void verwijder(ActionEvent event) {
        dc.removeItem();
    }

    @Override
    public void update(Object gebruiker) { //DTO hier?
        GebruikerDTO gebruikerDTO = (GebruikerDTO)gebruiker;
        txtGebruikersnaam.setText(gebruikerDTO.getGebruikersnaam());
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
        cbGeslacht.setValue(gebruikerDTO.getGeslacht());
        String password = gebruikerDTO.getWachtwoord();
        setPasswordVisibility(password==null);
        txtWachtwoord.setText(password);
    }
    
    private void setPasswordVisibility(boolean visibility){
        lblWachtwoord.setVisible(visibility);
        txtWachtwoord.setVisible(visibility);
    }

}
