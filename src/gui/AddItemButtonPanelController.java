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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bramd
 */
public class AddItemButtonPanelController extends HBox {

    private DomeinController dc;
    private MainPanelController mainPanel;
    private ActiviteitGegevensPanelController activiteitGegevensPanelController;
    
    @FXML
    private Button btnAddItem;

    public AddItemButtonPanelController(DomeinController dc, MainPanelController mainPanel){
        this.dc = dc;
        this.mainPanel = mainPanel;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddItemButtonPanel.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void addLid(ActionEvent event) {
        mainPanel.toonItem(new Lid(new GebruikerDTO()));
    }

    @FXML
    private void addItem(ActionEvent event) {
        final Stage scene = new Stage();
        VBox box = new VBox();
        box.getChildren().add(new ActiviteitGegevensPanelController(dc, mainPanel));
        Scene s = new Scene(box,300,300);
        scene.setScene(s);
        scene.show();
    }
}
