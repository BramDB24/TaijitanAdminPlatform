/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.List;

/**
 *
 * @author Jonah
 */
public interface OefeningInterface {

    public int getOefeningId();

    public String getNaam();

    public String getGraad();

    public int getOefeningType();

    public List<LesmateriaalInterface> getLesmateriaal();
    
    public List<GebruikerOefening> getRaadplegingen();

}
