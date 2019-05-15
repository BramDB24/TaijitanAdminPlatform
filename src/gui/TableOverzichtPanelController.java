/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author bramd
 */
public class TableOverzichtPanelController extends VBox {

    private final ChangeListener listener;
    private Class<?> klasse;
    private MainPanelController mainPanel;
    @FXML
    private TableView<Object> tableView;

    public TableOverzichtPanelController(MainPanelController mainPanel) {
        this.mainPanel = mainPanel;
        System.out.println(getClass().getResource("TableOverzichtPanel.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TableOverzichtPanel.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        listener = (ChangeListener) (ObservableValue observable, Object oldValue, Object newValue) -> {
            if (newValue != null) {
                mainPanel.toonItem(newValue);
            }
        };
    }

    public void setFields(List<String> fieldnames) {
        tableView.getColumns().clear();
        fieldnames.stream().map((field) -> {
            TableColumn<Object, String> column = new TableColumn<>((String) field);
            column.setCellValueFactory(new PropertyValueFactory<>((String) field));
            return column;
        }).forEachOrdered((column) -> {
            tableView.getColumns().add(column);
        });
    }

    public void setObservableList(ObservableList<Object> list) {
        if (list instanceof SortedList) {
            ((SortedList) list).comparatorProperty().bind(tableView.comparatorProperty());
        }
        klasse = list.get(0).getClass();
        setFields(mainPanel.getFieldNames(klasse));
        tableView.setItems(list);

    }

    public void enableListener() {
        tableView.getSelectionModel().selectedItemProperty().addListener(listener);
    }

    public void disableListener() {
        tableView.getSelectionModel().selectedItemProperty().removeListener(listener);
    }
}
