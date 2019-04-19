/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Jonah
 * @param <E>
 */
public class GenericDaoJpa<E> implements GenericDao<E> {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("TaijitanPU");
    protected static final EntityManager em = emf.createEntityManager();

    private final Class<E> type;

    public GenericDaoJpa(Class<E> type) {
        this.type = type;
    }

    public static void closePersistency() {
        em.close();
        emf.close();
    }

    public static void startTransaction() {
        em.getTransaction().begin();
    }

    public static void commitTransaction() {
        em.getTransaction().commit();
    }

    public static void rollbackTransaction() {
        em.getTransaction().rollback();
    }

    @Override
    public List<E> getAll() {
        return em.createQuery("select entity from" + type.getName() + "entity", type).getResultList();
    }

    @Override
    public <U> E get(U id) {
        E entity = em.find(type, id);
        return entity;
    }

    @Override
    public E update(E object) {
        return em.merge(object);
    }

    @Override
    public void delete(E object) {
        em.remove(em.merge(object));

    }

    @Override
    public void insert(E object) {
        em.persist(object);

    }

}
