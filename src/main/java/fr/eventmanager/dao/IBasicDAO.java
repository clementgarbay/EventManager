package fr.eventmanager.dao;

import fr.eventmanager.entity.StorableEntity;
import fr.eventmanager.core.persistence.QueryField;

import java.util.List;
import java.util.Optional;

/**
 * @author Clément Garbay
 */
public interface IBasicDAO<T extends StorableEntity> {
    Optional<T> findById(int id);
    Optional<T> findSingleByFields(QueryField... fields);
    List<T> findAll();
    List<T> findListByFields(QueryField... fields);
    boolean create(T element);
    boolean update(T element);
    boolean delete(int id);
    void close();
}
