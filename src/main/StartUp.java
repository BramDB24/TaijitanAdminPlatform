/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import gui.MainPanelController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author bramd
 */
public class StartUp extends Application {

    @Override
    public void start(Stage primaryStage) {
        //DomeinController dc = new DomeinController();
        Scene scene = new Scene(new MainPanelController(/*dc*/)); //hier moet startpagina komen ofc.
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
