/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein.DTO;

import java.time.LocalDateTime;

/**
 *
 * @author bramd
 */
public class ActiviteitDTO {
    private LocalDateTime datum;
    private String naam;
    private int aantalAanwezigen;
    
    public ActiviteitDTO(){
        
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public LocalDateTime getDatum() {
        return datum;
    }

    public void setDatum(LocalDateTime datum) {
        this.datum = datum;
    }

    public int getAantalAanwezigen() {
        return aantalAanwezigen;
    }

    public void setAantalAanwezigen(int aantalAanwezigen) {
        this.aantalAanwezigen = aantalAanwezigen;
    }
    
    
}
