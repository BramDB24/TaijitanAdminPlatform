/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import domein.Oefening;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Jonah
 */
public class LesmateriaalDaoJpa implements GenericDao<Oefening> {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("TaijitanPU");
    private final EntityManager entityManager = emf.createEntityManager();

    @Override
    public List<Oefening> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM Oefening e");
        return query.getResultList();
    }

    @Override
    public Optional<Oefening> get(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Oefening object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Oefening object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(Oefening object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
