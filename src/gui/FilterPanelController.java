/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author bramd
 */
public class FilterPanelController extends HBox {

    private DomeinController dc;
    private MainPanelController mainPanel;
    @FXML
    private ComboBox<String> cbFilter;
    @FXML
    private TextField txtFilter;

    public FilterPanelController(DomeinController dc, MainPanelController mainPanel){
        this.dc = dc;
        this.mainPanel = mainPanel;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FilterPanel.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        
        cbFilter.setValue("gebruikersnaam"); //wip
    }

    @FXML
    private void filter(KeyEvent event) {
        dc.changeFilter(cbFilter.getValue(), txtFilter.getText());
    }
    
    public void setValues(String[] fields){
        cbFilter.getItems().addAll(Arrays.asList(fields));
    }
    
}
