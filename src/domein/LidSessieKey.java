/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author bramd
 */
@Embeddable
public class LidSessieKey implements Serializable {

    @Column(name = "gebruikersnaam")
    private String gebruikersnaam;

    @Column(name = "SessieDatum")
    private LocalDateTime sessieDatum;

    // standard constructors, getters, and setters
    public LidSessieKey(){
        
    }
    // hashcode and equals implementation
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.gebruikersnaam);
        hash = 83 * hash + Objects.hashCode(this.sessieDatum);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LidSessieKey other = (LidSessieKey) obj;
        if (!Objects.equals(this.gebruikersnaam, other.gebruikersnaam)) {
            return false;
        }
        if (!Objects.equals(this.sessieDatum, other.sessieDatum)) {
            return false;
        }
        return true;
    }
    
}