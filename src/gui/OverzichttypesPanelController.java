/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Johanna
 */
public class OverzichttypesPanelController extends VBox {
    private MainPanelController mainPanel;
    @FXML
    private ComboBox<String> cbOverzicht;

    public OverzichttypesPanelController(MainPanelController mainPanel) {
        this.mainPanel = mainPanel;
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
        mainPanel.toonOverzicht(cbOverzicht.getSelectionModel().selectedItemProperty().getValue(), this);
    }

}
