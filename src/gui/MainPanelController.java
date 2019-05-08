/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import domein.GebruikerController;

import domein.OefeningController;
import domein.OverzichtController;
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
    private DomeinController gebruikerController;
    private DomeinController LesmateriaalController;
    private DomeinController OefeningController;
    private OverzichtPanelController overzichtPanel;
    private OverzichttypesPanelController opc;
    private LidGegevensPanelController lidGegevensPanel;
    private ParametersPanelController parametersPanel;

    @FXML
    private Button leden;
    @FXML
    private Button activiteiten;
    @FXML
    private Button aanwezigheden;
    @FXML
    private Button lesmateriaal;

    public MainPanelController(/*DomeinController dc*/) {
        //this.dc = dc;
        //gebruikerController = new GebruikerController();
        //LesmateriaalController = new LesmateriaalController();
        //OefeningController = new OefeningController();
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
        this.add(overzichtPanel, 1, 1);
    }

    /*private void toonAanwezigheden(ActionEvent event) {
        dc = new ActiviteitController();
        apc = new AanwezighedenPanelController(dc);
        this.add(apc, 1, 2);
    }*/
    public void toonItem(Object object) {
        lidGegevensPanel = new LidGegevensPanelController(dc);
        dc.addObserver(lidGegevensPanel);
        dc.toonItem(object);
        this.add(lidGegevensPanel, 2, 1);
    }

    public void toonNogItem(String keuze, OverzichttypesPanelController scherm) {
        switch (keuze) {
            case "Activiteiten":
                dc = new GebruikerController();
                break;
            case "Inschrijvingen":
                break;
            case "Aanwezigheden":
                break;
            case "ClubKampioenschap":
                break;
            case "Raadplegingen lesmateriaal":
                break;
        }
        if (!scherm.getChildren().stream().anyMatch(o -> o instanceof TableOverzichtPanelController)) {
            scherm.getChildren().add(new TableOverzichtPanelController(dc));
        }

        //parametersPanel = new ParametersPanelController(dc, this);
        //dc.addObserver(parametersPanel);
        //dc.toonItem(object); //Moet geïmplementeerd worden in de activiteitcontroller denk ikkk oof
        //this.add(parametersPanel, 1, 1);
    }

    @FXML
    private void toonOverzichtenlijst(ActionEvent event) {
        dc = new OverzichtController();
        opc = new OverzichttypesPanelController(dc, this);
        this.add(opc, 1, 0);
    }

    @FXML
    private void toonMateriaal(ActionEvent event) {
        dc = new OefeningController();
        overzichtPanel = new OverzichtPanelController(dc, this);
        this.add(overzichtPanel, 1, 1);
    }

}
