/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.ActiviteitController;
import domein.DomeinController;
import domein.GebruikerController;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Johanna
 */
public class MainPanelController extends GridPane {

    private DomeinController dc;
    private OverzichtPanelController overzichtPanel;
    private AanwezighedenPanelController apc;
    private LidGegevensPanelController lidGegevensPanel;
    
    @FXML
    private Button leden;
    @FXML
    private Button activiteiten;
    @FXML
    private Button aanwezigheden;

    public MainPanelController(/*DomeinController dc*/) {
        //this.dc = dc;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPanel.fxml"));

        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @FXML
    public void toonLedenlijst(ActionEvent event) {
        dc = new GebruikerController();
        overzichtPanel = new OverzichtPanelController(dc, this);
        this.add(overzichtPanel, 1, 0);
    }

    @FXML
    private void toonAanwezigheden(ActionEvent event) {
        dc = new ActiviteitController();
        apc = new AanwezighedenPanelController(dc);
        this.add(apc, 1, 1);
    }

    public void toonItem(Object object) {
        lidGegevensPanel = new LidGegevensPanelController(dc);
        dc.addObserver(lidGegevensPanel);
        dc.toonItem(object);
        this.add(lidGegevensPanel, 2, 0);
        
    }

    
}
