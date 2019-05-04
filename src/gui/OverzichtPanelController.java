/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author bramd
 */
public class OverzichtPanelController extends GridPane {
    private DomeinController dc;
    private MainPanelController mainPanel;
    
    @FXML
    private ListView<Object> listView;

    public OverzichtPanelController(DomeinController dc, MainPanelController mainPanel){
        this.dc = dc;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OverzichtPanel.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        listView.setItems(dc.toonOverzicht());
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listView.getSelectionModel().selectedItemProperty()
                .addListener(
                (observableValue, oldValue, newValue) -> {
                    if (newValue != null) {
                        mainPanel.toonItem(newValue);
                    }
                });
    }  
    
}
