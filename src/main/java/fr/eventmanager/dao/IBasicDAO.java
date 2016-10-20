package fr.eventmanager.dao;

import fr.eventmanager.entity.StorableEntity;

import java.util.List;
import java.util.Optional;

/**
 * @author Clément Garbay
 */
public interface IBasicDAO<T extends StorableEntity> {
    Optional<T> findById(int id);
    Optional<T> findSingleByFields(DbField... fields);
    List<T> findAll();
    List<T> findListByFields(DbField... fields);
    T create(T element);
    boolean update(T element);
    boolean delete(int id);
}
