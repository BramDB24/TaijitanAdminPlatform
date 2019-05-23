/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein.DTO;

import java.time.LocalDate;
/**
 *
 * @author bramd
 */
public class LidSessieDTO {
    private LocalDate datum;
    private String gebruikersnaam;
    

    public LocalDate getDatum(){
        return datum;
    }
    
    public void setDatum(LocalDate datum){
        this.datum = datum;
    }
    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }
}
