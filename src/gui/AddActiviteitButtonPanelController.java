/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.Activiteit;
import domein.DTO.ActiviteitDTO;
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
public class AddActiviteitButtonPanelController extends HBox {

    private MainPanelController mainPanel;
    @FXML
    private Button btnAddActiviteit;

    public AddActiviteitButtonPanelController(MainPanelController mainPanel){
        this.mainPanel = mainPanel;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddActiviteitButtonPanel.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @FXML
    private void addActviteit(ActionEvent event) {
        mainPanel.toonActiviteit(new Activiteit(new ActiviteitDTO()));
    }
    
    
    
    
}
