/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.ObservableList;
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

    @FXML
    private TableView<Object> tableView;

    public TableOverzichtPanelController() {
        System.out.println(getClass().getResource("TableOverzichtPanel.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TableOverzichtPanel.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void setFields(Class<?> klasse) {
        tableView.getColumns().clear();
        List<String> fields = new ArrayList<>();
        if (klasse.getSuperclass() != null) {
            Arrays.asList(klasse.getSuperclass().getDeclaredFields()).stream()
                    .map(field -> field.getName()).collect(Collectors.toList()).forEach(x -> fields.add((String) x));
        }
        Arrays.asList(klasse.getDeclaredFields()).stream()
                .map(field -> field.getName()).collect(Collectors.toList()).forEach(x -> fields.add((String) x));
        
        fields.stream().map((field) -> {
            TableColumn<Object, String> column = new TableColumn<>((String) field);
            column.setCellValueFactory(new PropertyValueFactory<>((String) field));
            return column;
        }).forEachOrdered((column) -> {
            tableView.getColumns().add(column);
        });
        
    }
    
    public void setObservableList(ObservableList<Object> list){
        setFields(list.get(0).getClass());
        tableView.setItems(list);
        
    }
}
