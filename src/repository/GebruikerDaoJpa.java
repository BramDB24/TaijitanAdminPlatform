/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import domein.Gebruiker;
import java.util.Collection;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

/**
 *
 * @author Jonah
 */
public class GebruikerDaoJpa extends GenericDaoJpa<Gebruiker> implements GebruikerDao {

    public GebruikerDaoJpa() {
        super(Gebruiker.class);
    }


    @Override
    public Collection<String> getAanwezigeGebruikers(int oneOrZero) throws EntityNotFoundException {
        try{
            return em.createNativeQuery("Gebruiker.getAanwezigheid", Gebruiker.class)
//                    .setParameter("oneOrZero",oneOrZero)
                    .getResultList();
        }catch(NoResultException e){
            throw new EntityNotFoundException();
        }
    }

}
