/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Jonah
 * @param <E>
 */
public class GenericDaoJpa<E> implements GenericDao<E> {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("TaijitanPU");
    private final EntityManager entityManager = emf.createEntityManager();

    private final Class<E> type;

    public GenericDaoJpa(Class<E> type) {
        this.type = type;
    }

    @Override
    public List<E> getAll() {
        return entityManager.createQuery("SELECT c FROM " + type.getName() + " c").getResultList();
    }

    @Override
    public Optional<E> get(long id) {
        return Optional.ofNullable(entityManager.find(type, id));
    }

    @Override
    public void update(E object) {
        executeInsideTransaction(e -> e.merge(object));
    }

    @Override
    public void delete(E object) {
        executeInsideTransaction(e -> e.remove(object));
    }

    @Override
    public void save(E object) {
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
