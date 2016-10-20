package fr.eventmanager.dao.impl;

import fr.eventmanager.dao.DbField;
import fr.eventmanager.dao.IBasicDAO;
import fr.eventmanager.dao.PersistenceManager;
import fr.eventmanager.entity.StorableEntity;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

/**
 * @author Clément Garbay
 */
public class BasicDAO<T extends StorableEntity> implements IBasicDAO<T> {

    private final PersistenceManager persistenceManager = PersistenceManager.getInstance();

    private Class<T> entityClassType;
    private CriteriaBuilder criteriaBuilder;

    public BasicDAO() {
        try {
            this.entityClassType = this.getEntityClassType();
            this.criteriaBuilder = persistenceManager.getCriteriaBuilder();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<T> findById(int id) {
        return findByFields(new DbField("id", id));
    }

    @Override
    public Optional<T> findByFields(DbField... fields) {
        CriteriaQuery<T> criteriaQuery = createCriteriaQuery();
        for (DbField field : fields) {
            criteriaQuery.where(criteriaBuilder.equal(getEntity(criteriaQuery).get(field.getFieldName()), field.getFieldValue()));
        }
        return Optional.ofNullable(getSingleResult(criteriaQuery));
    }

    @Override
    public List<T> findAll() {
        CriteriaQuery<T> criteriaQuery = createCriteriaQuery();
        criteriaQuery.select(getEntity(criteriaQuery));
        return getResultList(criteriaQuery);
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
        Optional<T> elementOptional = findById(element.getId());
        if (!elementOptional.isPresent()) return false;
        persistenceManager.getEntityManager().getTransaction().begin();
        persistenceManager.getEntityManager().merge(element);
        persistenceManager.getEntityManager().getTransaction().commit();
        return true;
    }

    @Override
    public boolean delete(int id) {
        Optional<T> elementOptional = findById(id);
        if (!elementOptional.isPresent()) return false;
        CriteriaDelete<T> criteriaDelete = createCriteriaDelete();
        criteriaDelete.where(criteriaBuilder.equal(getEntity(criteriaDelete).get("id"), id));
        return execute(criteriaDelete) != 0;
    }

    private Class<T> getEntityClassType() throws ClassNotFoundException {
        return ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    Root<T> getEntity(CriteriaQuery<T> criteriaQuery) {
        return criteriaQuery.from(entityClassType);
    }

    Root<T> getEntity(CriteriaUpdate<T> criteriaQuery) {
        return criteriaQuery.from(entityClassType);
    }

    Root<T> getEntity(CriteriaDelete<T> criteriaQuery) {
        return criteriaQuery.from(entityClassType);
    }

    CriteriaQuery<T> createCriteriaQuery() {
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClassType);
        return criteriaQuery.select(getEntity(criteriaQuery));
    }

    CriteriaUpdate<T> createCriteriaUpdate() {
        return criteriaBuilder.createCriteriaUpdate(entityClassType);
    }

    CriteriaDelete<T> createCriteriaDelete() {
        return criteriaBuilder.createCriteriaDelete(entityClassType);
    }

    int execute(CriteriaQuery<T> criteriaQuery) {
        Query query = persistenceManager.getEntityManager().createQuery(criteriaQuery);
        return query.executeUpdate();
    }

    int execute(CriteriaUpdate<T> criteriaQuery) {
        Query query = persistenceManager.getEntityManager().createQuery(criteriaQuery);
        return query.executeUpdate();
    }

    int execute(CriteriaDelete<T> criteriaQuery) {
        Query query = persistenceManager.getEntityManager().createQuery(criteriaQuery);
        return query.executeUpdate();
    }

    T getSingleResult(CriteriaQuery<T> criteriaQuery) {
        Query query = persistenceManager.getEntityManager().createQuery(criteriaQuery);
        query.setMaxResults(1);
        List<T> list = query.getResultList();
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    List<T> getResultList(CriteriaQuery<T> criteriaQuery) {
        Query query = persistenceManager.getEntityManager().createQuery(criteriaQuery);
        return (List<T>) query.getResultList();
    }

}
