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
public class RaadplegingenDTO {
    private LocalDateTime tijdstip;
    private String gebruikersnaam;
    private String naam;
    private String voornaam;
    private String oefening;

    public LocalDateTime getTijdstip() {
        return tijdstip;
    }

    public void setTijdstip(LocalDateTime tijdstip) {
        this.tijdstip = tijdstip;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getOefening() {
        return oefening;
    }

    public void setOefening(String oefening) {
        this.oefening = oefening;
    }
    
    
}
