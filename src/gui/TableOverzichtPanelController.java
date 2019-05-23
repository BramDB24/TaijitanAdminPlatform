/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
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
    private SortedList sortedList;

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

    public void setFields(List<String> fieldnames, Map<String, String> methodnames) { //methodnames is map met methodname als key en fieldname als value
        tableView.getColumns().clear();
        fieldnames.stream().map((field) -> {
            TableColumn<Object, String> column = new TableColumn<>((String) field);
            column.setCellValueFactory(new PropertyValueFactory<>((String) field));
            return column;
        }).forEachOrdered((column) -> {
            tableView.getColumns().add(column);
        });

        methodnames.forEach((key, val) -> {
            TableColumn<Object, String> column = new TableColumn<>((String) val);
            column.setCellValueFactory((x) -> {
                ObservableValue<String> methodvalue = null;
                try {
                    methodvalue = (ObservableValue<String>) x.getValue().getClass().getMethod(key).invoke(x.getValue());
                } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    return new SimpleObjectProperty<>("/");
                }
                return methodvalue;
            });
            //new PropertyValueFactory<>((String) key));
            tableView.getColumns().add(column);
        });
    }

    public void setObservableList(ObservableList<Object> list) {
        
        //if (list instanceof SortedList) {
        //    ((SortedList) list).comparatorProperty().bind(tableView.comparatorProperty());
        //}
        if(sortedList != null){
            sortedList.comparatorProperty().unbind();
        }
        if (list != null) {
            sortedList = list.sorted();
            sortedList.comparatorProperty().bind(tableView.comparatorProperty());
            tableView.setItems(sortedList);
            if (!list.isEmpty()) {
                klasse = list.get(0).getClass();
                setFields(mainPanel.getFieldNames(klasse), mainPanel.getMethodNames(klasse));
            }
            else{
                tableView.setPlaceholder(new Label("Geen overzicht beschikbaar!"));
            }
        }
    }

    public void enableListener() {
        tableView.getSelectionModel().selectedItemProperty().addListener(listener);
    }

    public void disableListener() {
        tableView.getSelectionModel().selectedItemProperty().removeListener(listener);
    }

}
