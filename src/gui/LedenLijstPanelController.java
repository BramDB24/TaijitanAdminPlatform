/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author bramd
 */
public class LedenLijstPanelController extends GridPane {

    private DomeinController dc;
    private LidGegevensPanelController lgpc;

    @FXML
    private ListView<String> listViewLeden;
    @FXML
    private Button toevoegen;

    public LedenLijstPanelController(DomeinController dc) {
        this.dc = dc;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("LedenLijstPanel.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        listViewLeden.setItems(dc.getLeden());
        lgpc = new LidGegevensPanelController(dc);
        listViewLeden.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listViewLeden.getSelectionModel().selectedItemProperty().
                addListener(val -> {
                    if (val != null) {
                        try {
                            lgpc.update(dc.getLidInfo(listViewLeden.getSelectionModel().getSelectedItem()));
                        } catch (NullPointerException e) {
                            System.out.println("Er kan geen info getoont worden voor dit lid.");
                        }
                    }
                });
    }

    @FXML
    private void handleMouseClick(javafx.scene.input.MouseEvent event) {
        this.getChildren().remove(lgpc);
        this.add(lgpc, 1, 1);
    }

    @FXML
    private void toevoegenLid(ActionEvent event) {
        this.getChildren().remove(lgpc);
        lgpc = new LidGegevensPanelController(dc);
        this.add(lgpc, 1, 1);
    }
}
