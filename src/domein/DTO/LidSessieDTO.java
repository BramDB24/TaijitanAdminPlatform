/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein.DTO;

/**
 *
 * @author bramd
 */
public class LidSessieDTO {
    private String gebruikersnaam;
    private boolean aanwezig;

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public boolean isAanwezig() {
        return aanwezig;
    }

    public void setAanwezig(boolean aanwezig) {
        this.aanwezig = aanwezig;
    }
    
}
