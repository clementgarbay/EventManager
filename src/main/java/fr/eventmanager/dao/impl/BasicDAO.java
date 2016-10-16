package fr.eventmanager.dao.impl;

import fr.eventmanager.dao.IBasicDAO;
import fr.eventmanager.dao.PersistenceManager;
import fr.eventmanager.model.StorableEntity;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

/**
 * @author Clément Garbay
 */
public class BasicDAO<T extends StorableEntity<T>> implements IBasicDAO<T> {

    protected final PersistenceManager persistenceManager = PersistenceManager.getInstance();

    private String tableName;
    private Class classType;

    public BasicDAO(String tableName, Class<T> classType) {
        this.tableName = tableName;
        this.classType = classType;
    }

    @Override
    public Optional<T> find(int id) {
        T element = (T) persistenceManager.getEntityManager().find(classType, id);
        if (element == null) return Optional.empty();
        return Optional.of(element);
    }

    @Override
    public List<T> findAll() {
        Query query = persistenceManager.getEntityManager().createQuery("SELECT e FROM " + tableName + " e");
        return (List<T>) query.getResultList();
    }

    @Override
    public T create(T element) {
        persistenceManager.getEntityManager().getTransaction().begin();
        persistenceManager.getEntityManager().persist(element);
        persistenceManager.getEntityManager().getTransaction().commit();
        return element;
    }

    @Override
    public boolean update(T element) {
        Optional<T> elementOptional = find(element.getId());
        if (!elementOptional.isPresent()) return false;
        persistenceManager.getEntityManager().getTransaction().begin();

        // TOREVIEW : update only the modified fields
        elementOptional.get().populateFrom(element);

        persistenceManager.getEntityManager().getTransaction().commit();
        return true;
    }

    @Override
    public boolean delete(int id) {
        Optional<T> elementOptional = find(id);
        if (!elementOptional.isPresent()) return false;
        persistenceManager.getEntityManager().getTransaction().begin();
        persistenceManager.getEntityManager().remove(elementOptional.get());
        persistenceManager.getEntityManager().getTransaction().commit();
        return true;
    }
}
