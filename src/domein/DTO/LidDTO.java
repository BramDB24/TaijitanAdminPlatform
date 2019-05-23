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
public class LidDTO {
    private String gebruikersnaam;
    private int graad;
    private String formule;
    
    public LidDTO(){
        
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public int getGraad() {
        return graad;
    }

    public void setGraad(int graad) {
        this.graad = graad;
    }

    public String getFormule() {
        return formule;
    }

    public void setFormule(String formule) {
        this.formule = formule;
    }
    
    
}
