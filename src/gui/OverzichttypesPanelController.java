/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Johanna
 */
public class OverzichttypesPanelController extends GridPane {
    private DomeinController dc;
    private MainPanelController mainPanel;
    
    @FXML
    private ComboBox<String> cbOverzicht;

    public OverzichttypesPanelController (DomeinController dc, MainPanelController mainPanel) {
        this.dc = dc;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OverzichttypesPanel.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        cbOverzicht.getItems().addAll(
            "Activiteiten",
            "Inschrijvingen",
            "Aanwezigheden",
            "Clubkampioenschap",
            "Raadplegingen lesmateriaal");
    }

    @FXML
    private void overzichtklik(ActionEvent event) {
        cbOverzicht.getSelectionModel().selectedItemProperty()
                .addListener(
                (observableValue, oldValue, newValue) -> {
                    if (newValue != null) {
                        mainPanel.toonNogItem(newValue);
                    }
                });
    }

}
