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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author bramd
 */
public class ActiviteitGegevensPanelController extends GridPane {

    @FXML
    private TextField txtNaam;
    @FXML
    private TextField txtStartdatum;
    @FXML
    private TextField txtEinddatum;
    @FXML
    private TextField txtMaxAantal;
    @FXML
    private Button btnAnnuleer;
    @FXML
    private Button btnOpslaan;

    private MainPanelController mainPanel;
    private DomeinController dc;
    
    public ActiviteitGegevensPanelController(DomeinController dc, MainPanelController mainPanel){
        this.dc = dc;
        this.mainPanel = mainPanel;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ActiviteitGegevensPanel.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } 
    }
    @FXML
    private void annuleer(ActionEvent event) {
    }

    @FXML
    private void slaOp(ActionEvent event) {
    }
    
}
