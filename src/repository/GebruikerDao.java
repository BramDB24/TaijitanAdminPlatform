/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import domein.Gebruiker;
import java.util.Collection;
import javax.persistence.EntityNotFoundException;

/**
 *
 * @author Jonah
 */
public interface GebruikerDao extends GenericDao<Gebruiker>  {
    public Collection<String> getAanwezigeGebruikers(int oneOrZero) throws EntityNotFoundException;
    
}
