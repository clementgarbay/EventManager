package fr.eventmanager.dao;

import fr.eventmanager.entity.StorableEntity;

import java.util.List;
import java.util.Optional;

/**
 * @author Clément Garbay
 */
public interface IBasicDAO<T extends StorableEntity> {
    Optional<T> find(int id);
    List<T> findAll();
    T create(T element);
    boolean update(T element);
    boolean delete(int id);
}
