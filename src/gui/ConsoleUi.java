/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.util.Scanner;

/**
 *
 * @author Jonah
 */
public class ConsoleUi {
     private final DomeinController domeinController;
    private final Scanner in = new Scanner(System.in);
    public ConsoleUi(DomeinController dc) {
       domeinController = dc;
    }

    public void run() {
        doStandardJob();
        //domeinController.close();
    }

    private void doStandardJob() {
        //System.out.println("Gebruikers :");
        //System.out.println(domeinController.getGebruikerNamen());  
        //System.out.println("Aanwezigen :");
        //System.out.println(domeinController.getAanwezighedenGebruikers(0));
        //System.out.println("Lesmateriaal");
        //System.out.println(domeinController.getLesmateriaal());
        
    }
}
