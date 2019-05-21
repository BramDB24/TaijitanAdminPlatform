/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.Observer;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Johanna
 */
public class ParametersPanelController extends GridPane implements Observer{
    private MainPanelController mainPanel;
    
    @FXML
    private DatePicker datumParameter;
    @FXML
    private Button toonOverzicht;
    
    public ParametersPanelController (MainPanelController mainPanel) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ParametersPanel.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        
        
    }

    @Override
    public void update(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
