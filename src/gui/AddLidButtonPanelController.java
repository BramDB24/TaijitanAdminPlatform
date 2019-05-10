/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DTO.GebruikerDTO;
import domein.DomeinController;
import domein.GebruikerController;
import domein.Lid;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author bramd
 */
public class AddLidButtonPanelController extends HBox {

    private DomeinController dc;
    private MainPanelController mainPanel;
    
    @FXML
    private Button btnAddLid;

    public AddLidButtonPanelController(DomeinController dc, MainPanelController mainPanel){
        this.dc = dc;
        this.mainPanel = mainPanel;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddLidButtonPanel.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @FXML
    private void addLid(ActionEvent event) {
        mainPanel.toonItem(new Lid(new GebruikerDTO()));
    }
}
