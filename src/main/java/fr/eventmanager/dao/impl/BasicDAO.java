package fr.eventmanager.dao.impl;

import fr.eventmanager.dao.IBasicDAO;
import fr.eventmanager.dao.PersistenceManager;
import fr.eventmanager.entity.StorableEntity;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

/**
 * @author Clément Garbay
 */
public class BasicDAO<T extends StorableEntity> implements IBasicDAO<T> {

    protected final PersistenceManager persistenceManager = PersistenceManager.getInstance();

    protected String tableName;
    protected Class<T> entityClassType;
    protected CriteriaQuery<T> createQuery;

    public BasicDAO() {
        try {
            this.entityClassType = this.getEntityClassType();
            this.createQuery = persistenceManager.getCriteriaBuilder().createQuery(entityClassType);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<T> find(int id) {
        Root<T> entity = createQuery.from(entityClassType);
        createQuery.select(entity)
                .where( persistenceManager.getCriteriaBuilder().equal(entity.get("id"), id));
        Query query = persistenceManager.getEntityManager().createQuery(createQuery);

        return Optional.ofNullable((T) query.getSingleResult());
    }

    @Override
    public List<T> findAll() {
        Query query = persistenceManager.getEntityManager().createQuery(String.format("SELECT e FROM %s e", tableName));
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
        persistenceManager.getEntityManager().merge(element);
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

    private Class<T> getEntityClassType() throws ClassNotFoundException {
        return ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }
}
