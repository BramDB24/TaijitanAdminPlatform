/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author bramd
 */
public class ActiviteitDTO {
    private LocalDate startDatum;
    private LocalDate eindDatum;
    private int maxAantal;
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
    
    public int getAantalAanwezigen() {
        return aantalAanwezigen;
    }

    public void setAantalAanwezigen(int aantalAanwezigen) {
        this.aantalAanwezigen = aantalAanwezigen;
    }

    public LocalDate getStartDatum() {
        return startDatum;
    }

    public LocalDate getEindDatum() {
        return eindDatum;
    }

    public int getMaxAantal() {
        return maxAantal;
    }

    public void setStartDatum(LocalDate startDatum) {
        this.startDatum = startDatum;
    }

    public void setEindDatum(LocalDate eindDatum) {
        this.eindDatum = eindDatum;
    }

    public void setMaxAantal(int maxAantal) {
        this.maxAantal = maxAantal;
    }
    
    
    
    
}
