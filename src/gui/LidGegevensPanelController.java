package gui;

import domein.DTO.GebruikerDTO;
import domein.GebruikerController;
import domein.Observer;
import java.io.IOException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LidGegevensPanelController extends GridPane implements Observer {

    private final GebruikerController dc;
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
    @FXML
    private ComboBox<String> cbGraad;
    @FXML
    private TextField txtScore;

    public LidGegevensPanelController(GebruikerController dc) {
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
        cbGeslacht.getItems().addAll("Man", "Vrouw", "Onbepaald");
        cbGraad.getItems().addAll("Kyu6", "Kyu5", "Kyu4", "Kyu3", "Kyu2", "Kyu1", "Dan1", "Dan2", "Dan3", "Dan4", "Dan5", "Dan6", "Dan7", "Dan8");
        txtGebruikersnaam.setDisable(true);
    }

    @FXML
    private void slaOp(ActionEvent event) {
        GebruikerDTO dto = new GebruikerDTO();
        //gebruikersnaam mag niet veranderen
        if (txtGebruikersnaam.getText() == null || txtGebruikersnaam.getText().isEmpty() || txtGebruikersnaam.getText().trim() == "") {
            txtGebruikersnaam.setText(txtVoornaam.getText() + "." + txtFamilienaam.getText());
        }
        String gebruikersnaam = txtGebruikersnaam.getText();
        dto.setGebruikersnaam(gebruikersnaam);
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
        dto.setGeslacht(cbGeslacht.getValue());
        dto.setGraad(cbGraad.getValue());
        dto.setInschrijvingsdatum(dateInschrijving.getValue());
        dto.setWachtwoord(txtWachtwoord.getText());
        dto.setScore(Integer.parseInt(txtScore.getText()));
        try {
            dc.editItem(dto);
        } catch (IllegalArgumentException exception) {
            new Alert(Alert.AlertType.ERROR, exception.getMessage()).showAndWait();
        }
        String password = dto.getWachtwoord();
        setPasswordVisibility(password == null);
        txtWachtwoord.setText(password);
    }

    @FXML
    private void verwijder(ActionEvent event) {
        ButtonType ja = new ButtonType("Ja", ButtonBar.ButtonData.OK_DONE);
        ButtonType annuleren = new ButtonType("Annuleren", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(AlertType.CONFIRMATION, "Verwijderen gebruiker", ja, annuleren);
        alert.setTitle("Gebruiker verwijderen");
        alert.setHeaderText("Ben je zeker dat je " + txtGebruikersnaam.getText() + " wilt verwijderen?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ja) {
            dc.removeItem();
        }
    }

    @Override
    public void update(Object gebruiker) { //DTO hier?
        GebruikerDTO gebruikerDTO = (GebruikerDTO) gebruiker;
        txtGebruikersnaam.setText(gebruikerDTO.getGebruikersnaam());
        txtFamilienaam.setText(gebruikerDTO.getNaam());
        txtVoornaam.setText(gebruikerDTO.getVoornaam());
        txtTelefoon.setText(gebruikerDTO.getTelefoonnummer());
        dateGeboorte.setValue(gebruikerDTO.getGeboortedatum());
        txtEmail.setText(gebruikerDTO.getEmail());
        cbGraad.setValue(gebruikerDTO.getGraad());
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
        setPasswordVisibility(password == null);
        txtWachtwoord.setText(password);
        txtScore.setText(Integer.toString(gebruikerDTO.getScore()));
    }

    private void setPasswordVisibility(boolean visibility) {
        lblWachtwoord.setVisible(visibility);
        txtWachtwoord.setVisible(visibility);
    }

}
