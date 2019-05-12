/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.ActiviteitController;
import domein.DomeinController;
import domein.Gebruiker;
import domein.GebruikerController;

import domein.OefeningController;
import domein.OverzichtController;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import javafx.collections.ObservableList;
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
    private TableOverzichtPanelController tableOverzichtPanel;

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

    public void clearScreen() {
        this.getChildren().removeAll(lidGegevensPanel, parametersPanel, tableOverzichtPanel, opc, overzichtPanel);
    }

    @FXML
    public void toonLedenlijst(ActionEvent event) {
        this.clearScreen();
        dc = new GebruikerController();
        OverzichtController<Object> oc = new OverzichtController<>();
        if (tableOverzichtPanel == null) {
            tableOverzichtPanel = new TableOverzichtPanelController(this);
        }
        if (!this.getChildren().stream().anyMatch(o -> o instanceof TableOverzichtPanelController)) {
            this.add(tableOverzichtPanel, 1, 1);
        }
        tableOverzichtPanel.setObservableList(oc.toonGebruikers());
        tableOverzichtPanel.enableListener();

//        dc = new GebruikerController();
//        overzichtPanel = new OverzichtPanelController(dc, this);
//        this.add(overzichtPanel, 1, 1);
        //this.add(new AddLidButtonPanelController(dc, this), 1, 2);
    }

    /*private void toonAanwezigheden(ActionEvent event) {
        dc = new ActiviteitController();
        apc = new AanwezighedenPanelController(dc);
        this.add(apc, 1, 2);
    }*/
    public void toonItem(Object object) {
        this.getChildren().remove(lidGegevensPanel);
        lidGegevensPanel = new LidGegevensPanelController(dc);
        dc.addObserver(lidGegevensPanel);
        dc.toonItem(object);
        this.add(lidGegevensPanel, 2, 1);
    }

    public void toonNogItem(String keuze, OverzichttypesPanelController scherm) {
        OverzichtController<Object> oc = new OverzichtController<>();
        if (tableOverzichtPanel == null) {
            tableOverzichtPanel = new TableOverzichtPanelController(this);
        }
        if (!this.getChildren().stream().anyMatch(o -> o instanceof TableOverzichtPanelController)) {
            this.add(tableOverzichtPanel, 1, 1);
        }
        switch (keuze) {
            case "Activiteiten":
                tableOverzichtPanel.setObservableList(oc.toonActiviteitenOverzicht());
                break;
            case "Inschrijvingen":
                break;
            case "Aanwezigheden":
                tableOverzichtPanel.setObservableList(oc.toonAanwezighedenOverzicht(LocalDateTime.of(2019, Month.APRIL, 24, 0, 0)));
                break;
            case "Clubkampioenschap":
                tableOverzichtPanel.setObservableList(oc.toonClubkampioenschapOverzicht());
                break;
            case "Raadplegingen lesmateriaal":
                break;
        }
        tableOverzichtPanel.disableListener();

        //parametersPanel = new ParametersPanelController(dc, this);
        //dc.addObserver(parametersPanel);
        //dc.toonItem(object); //Moet geïmplementeerd worden in de activiteitcontroller denk ikkk oof
        //this.add(parametersPanel, 1, 1);
    }

    @FXML
    private void toonOverzichtenlijst(ActionEvent event) {
        this.clearScreen();
        dc = new OverzichtController();
        opc = new OverzichttypesPanelController(dc, this);
        this.add(opc, 1, 0);
        tableOverzichtPanel.disableListener();
    }

    @FXML
    private void toonMateriaal(ActionEvent event) {
        this.clearScreen();
        dc = new OefeningController();
        overzichtPanel = new OverzichtPanelController(dc, this);
        this.add(overzichtPanel, 1, 1);
    }

    @FXML
    private void beheerActiviteiten(ActionEvent event) {
        this.clearScreen();
        dc = new ActiviteitController();
        tableOverzichtPanel = new TableOverzichtPanelController(this);
        tableOverzichtPanel.setObservableList(dc.toonOverzicht());
        tableOverzichtPanel.disableListener();
        this.add(tableOverzichtPanel, 1, 1);
    }
}
