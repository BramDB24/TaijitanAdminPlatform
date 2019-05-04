/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Jonah
 * @param <E>
 */
public interface GenericDao<E> {

    List<E> getAll();

    Optional<E> get(long id);

    void update(E object);

    void delete(E object);
    
    void save(E object);
}
