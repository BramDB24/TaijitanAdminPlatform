/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import domein.Gebruiker;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author Jonah
 */
public class UserDaoJpa implements GenericDao<Gebruiker> {

    private EntityManager entityManager;

    @Override
    public List<Gebruiker> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM Gebruiker e");
        return query.getResultList();
    }

    @Override
    public Optional<Gebruiker> get(long id) {
        return Optional.ofNullable(entityManager.find(Gebruiker.class, id));
    }

    @Override
    public void update(Gebruiker object) {
        executeInsideTransaction(e -> e.persist(object));
    }

    @Override
    public void delete(Gebruiker object) {
        executeInsideTransaction(e -> e.remove(object));
    }

    @Override
    public void save(Gebruiker object) {
        executeInsideTransaction(e -> e.persist(object));
    }

    private void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            action.accept(entityManager);
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }

}
