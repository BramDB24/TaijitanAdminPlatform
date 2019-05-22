/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.Activiteit;
import domein.ActiviteitController;
import domein.DTO.ActiviteitDTO;
import domein.DTO.GebruikerDTO;
import domein.DomeinController;
import domein.GebruikerController;
import domein.Lid;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author bramd
 */
public class AddItemButtonPanelController extends HBox {

    private MainPanelController mainPanel;
    private DomeinController dc;

    @FXML
    private Button btnAddItem;

    public AddItemButtonPanelController(MainPanelController mainPanel, DomeinController dc) {

        this.mainPanel = mainPanel;
        this.dc = dc;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddItemButtonPanel.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void addElement() {
        if (dc instanceof ActiviteitController) {
            mainPanel.toonItem(new Activiteit(new ActiviteitDTO()));
        } else if (dc instanceof GebruikerController) {
            mainPanel.toonItem(new Lid(new GebruikerDTO()));

        }
    }

    @FXML
    private void addItem(ActionEvent event) {
        addElement();
    }
}
