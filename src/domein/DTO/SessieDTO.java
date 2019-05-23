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
public class SessieDTO {
    private String type;
    private LocalDateTime datum;
    private int aanwezigen;
    
    public SessieDTO(){
        
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getDatum() {
        return datum;
    }

    public void setDatum(LocalDateTime datum) {
        this.datum = datum;
    }

    public int getAanwezigen() {
        return aanwezigen;
    }

    public void setAanwezigen(int aanwezigen) {
        this.aanwezigen = aanwezigen;
    }
    
    
}
