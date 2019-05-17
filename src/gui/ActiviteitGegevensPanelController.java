/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import domein.GebruikerController;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
    @FXML
    private Button btnVerwijder;

    public ActiviteitGegevensPanelController(MainPanelController mainPanel) {
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
        TableOverzichtPanelController op = new TableOverzichtPanelController(mainPanel);
        GebruikerController g = new GebruikerController();
        final Stage scene = new Stage();
        VBox box = new VBox();
        op.setObservableList(g.toonOverzicht());
        box.getChildren().add(op);
        Scene s = new Scene(box, 400, 600); //Bram is een beestje :333
        scene.setScene(s);
        scene.show();
        Stage s2 = (Stage)this.getScene().getWindow();
        s2.close(); 
    }

    @FXML
    private void verwijder(ActionEvent event) {
    }

}
