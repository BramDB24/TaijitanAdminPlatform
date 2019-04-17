/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.util.List;

/**
 *
 * @author Jonah
 * @param <E>
 */
public interface GenericDao<E> {

    public List<E> getAll();

    public <U> E get(U id);

    public E update(E object);

    public void delete(E object);

    public void insert(E object);

}
