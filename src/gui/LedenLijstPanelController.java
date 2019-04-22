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
public class LedenLijstPanelController extends GridPane {

    private DomeinController dc;
    private LidGegevensPanelController lgpc;

    @FXML
    private ListView<String> listViewLeden;

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

        lgpc = new LidGegevensPanelController(dc);

        listViewLeden.setItems(dc.getLeden());
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
        this.add(lgpc, 1, 0);
    }
}
