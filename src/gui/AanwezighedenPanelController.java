/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Jonah
 */
public class AanwezighedenPanelController extends GridPane {

    @FXML
    private RadioButton radioAanwezig;
    @FXML
    private RadioButton radioAfwezig;
    @FXML
    private ListView<String> aanwezighedenListview;
    @FXML
    private Label titleLable;

    private DomeinController dc;
    @FXML
    private ToggleGroup toggleGroup;

    public AanwezighedenPanelController(DomeinController dc) {
        this.dc = dc;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AanwezighedenPanel.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        aanwezighedenListview.setItems(dc.getAanwezighedenGebruikers(computeRadiobuttonValue()));

    }

    public final int computeRadiobuttonValue() {
        RadioButton selected = (RadioButton) toggleGroup.getSelectedToggle();
        switch (selected.getText()) {
            case "Aanwezig":
                return 1;
            case "Afwezig":
                return 0;
            default:
                return 1;
        }
    }

    @FXML
    private void computeAanwezig(ActionEvent event) {
        aanwezighedenListview.setItems(dc.getAanwezighedenGebruikers(1));

    }

    @FXML
    private void computeAfwezig(ActionEvent event) {
        aanwezighedenListview.setItems(dc.getAanwezighedenGebruikers(0));

    }

}
