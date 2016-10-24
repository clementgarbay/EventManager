package fr.eventmanager.utils.persistence;

import fr.eventmanager.entity.StorableEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Clément Garbay
 */
public class DatabaseManager<T extends StorableEntity> {

    public enum Action { CREATE, READ, UPDATE, DELETE }

    protected Class<T> entityClassType;
    protected CriteriaBuilder criteriaBuilder;
    protected EntityManager entityManager;

    public DatabaseManager(String persistenceUnitName) {
        try {
            PersistenceManager persistenceManager = new PersistenceManager(persistenceUnitName);

            this.entityClassType = this.getEntityClassType();
            this.criteriaBuilder = persistenceManager.getCriteriaBuilder();
            this.entityManager = persistenceManager.getEntityManager();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void begin() {
        entityManager.getTransaction().begin();
    }

    public void commit() {
        entityManager.getTransaction().commit();
    }

    public BaseQuery<T> getBaseQuery(Action action) {

        if (action.equals(Action.READ)) {
            CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(this.entityClassType);
            Root<T> root = criteriaQuery.from(this.entityClassType);

            return new BaseQuery<>(root, criteriaQuery);
        }

        if (action.equals(Action.UPDATE)) {
            CriteriaUpdate<T> criteriaQuery = criteriaBuilder.createCriteriaUpdate(this.entityClassType);
            Root<T> root = criteriaQuery.from(this.entityClassType);

            return new BaseQuery<>(root, criteriaQuery);
        }

        if (action.equals(Action.DELETE)) {
            CriteriaDelete<T> criteriaQuery = criteriaBuilder.createCriteriaDelete(this.entityClassType);
            Root<T> root = criteriaQuery.from(this.entityClassType);

            return new BaseQuery<>(root, criteriaQuery);
        }

        // TODO : throw Exception
        return new BaseQuery<>(null, null);
    }

    public Query getReadQueryBy(QueryField... fields) {
        BaseQuery<T> baseQuery = getBaseQuery(Action.READ);
        List<Predicate> predicates = new ArrayList<>();

        for (QueryField field : fields) {
            switch (field.getFilter()) {
                case LIKE:
                    predicates.add(criteriaBuilder.like(baseQuery.getEntity().get(field.getName()), field.getValue().toString()));
                    break;
                case EQUAL:
                default:
                    predicates.add(criteriaBuilder.equal(baseQuery.getEntity().get(field.getName()), field.getValue()));
                    break;
            }
        }

        CriteriaQuery<T> criteriaQuery = (CriteriaQuery<T>) baseQuery.getAbstractCriteria();
        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));

        return entityManager.createQuery(criteriaQuery);
    }

    protected T getSingleResult(Query query) {
        query.setMaxResults(1);
        List<T> list = query.getResultList();
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    protected List<T> getResultList(Query query) {
        return (List<T>) query.getResultList();
    }

    protected boolean executeQuery(Query query) {
        return query.executeUpdate() != 0;
    }

    private Class<T> getEntityClassType() throws ClassNotFoundException {
        return ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

}
