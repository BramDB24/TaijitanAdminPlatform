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
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Johanna
 */
    public class MainPanelController extends GridPane {

    private DomeinController dc;
    private LedenLijstPanelController llpc;
    private AanwezighedenPanelController apc;
    @FXML
    private Button leden;
    @FXML
    private Button activiteiten;
    @FXML
    private Button aanwezigheden;

    public MainPanelController(DomeinController dc) {
        this.dc = dc;
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
        llpc = new LedenLijstPanelController(dc);
        this.add(llpc, 1, 1);
    }

    @FXML
    private void toonAanwezigheden(ActionEvent event) {
        apc = new AanwezighedenPanelController(dc);
        this.add(apc, 1, 1);
    }

    /**
     * Initializes the controller class.
     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//    }    
}
