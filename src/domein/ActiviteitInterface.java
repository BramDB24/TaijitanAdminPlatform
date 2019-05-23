/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.time.LocalDate;
import java.util.List;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author bramd
 */
public interface ActiviteitInterface {
    
    public String getNaam();
    
    public LocalDate getStartDatum();
    
    public ObservableValue<String> getAantalAanwezigen();
    
    public int getMaxAantal();
    
    public List<Gebruiker> getAanwezigen();
}
