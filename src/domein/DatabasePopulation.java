/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.Date;
import repository.GebruikerDaoJpa;

/**
 *
 * @author Jonah
 */
public class DatabasePopulation {

    public void seedDb() {
        GebruikerDaoJpa gdj = new GebruikerDaoJpa();
        GebruikerDaoJpa.startTransaction();
            
        gdj.insert(new Lid("De Smet", "Jonah","TestWachtwoord", new Date(), "straat", 0000, "België", 123456789, "emai@email.com", "054124578", "GeboortePlek", 1, "Stad", "Belg", "emailOuders@email.com", "0476124578", 'M'));

        GebruikerDaoJpa.commitTransaction();

    }
}
