package fr.eventmanager.core.persistence;

import fr.eventmanager.entity.StorableEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static java.util.Objects.isNull;

/**
 * @author Clément Garbay
 */
public class PersistenceManager<T extends StorableEntity> {

    protected enum Action { CREATE, READ, UPDATE, DELETE }

    private EntityManagerFactory entityManagerFactory;
    private Class<T> entityClassType;
    protected EntityManager entityManager;
    protected CriteriaBuilder criteriaBuilder;

    public PersistenceManager(String persistenceUnitName) {
        try {
            this.entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
            this.entityClassType = this.getEntityClassType();
            this.entityManager = entityManagerFactory.createEntityManager();
            this.criteriaBuilder = entityManagerFactory.getCriteriaBuilder();
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

    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }

    public <C extends CommonAbstractCriteria> BaseQuery<T,C> getBaseQuery(Action action) {

        if (action.equals(Action.READ)) {
            CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(this.entityClassType);
            Root<T> root = criteriaQuery.from(this.entityClassType);

            return new BaseQuery<>(root, (C) criteriaQuery);
        }

        if (action.equals(Action.UPDATE)) {
            CriteriaUpdate<T> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(this.entityClassType);
            Root<T> root = criteriaUpdate.from(this.entityClassType);

            return new BaseQuery<>(root, (C) criteriaUpdate);
        }

        if (action.equals(Action.DELETE)) {
            CriteriaDelete<T> criteriaDelete = criteriaBuilder.createCriteriaDelete(this.entityClassType);
            Root<T> root = criteriaDelete.from(this.entityClassType);

            return new BaseQuery<>(root, (C) criteriaDelete);
        }

        // TODO : throw Exception
        return new BaseQuery<>(null, null);
    }

    public Query getReadQueryBy(QueryField... fields) {
        BaseQuery<T, CriteriaQuery<T>> baseQuery = getBaseQuery(Action.READ);
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

        CriteriaQuery<T> criteriaQuery = baseQuery.getAbstractCriteria()
                .where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));

        return entityManager.createQuery(criteriaQuery);
    }

    protected T getSingleResult(Query query) {
        query.setMaxResults(1);
        List<T> list = query.getResultList();
        return (isNull(list) || list.isEmpty()) ? null : list.get(0);
    }

    protected List<T> getResultList(Query query) {
        return (List<T>) query.getResultList();
    }

    protected boolean proceedToQuery(Consumer<EntityManager> consumer) {
        try {
            begin();
            consumer.accept(entityManager);
            entityManager.flush();
            commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Class<T> getEntityClassType() throws ClassNotFoundException {
        return ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

}
