package fr.eventmanager.utils.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;

/**
 * @author Clément Garbay
 *
 * TODO : useful ?
 */
public class PersistenceManager {

    private EntityManagerFactory emf;
    private EntityManager em;
    private CriteriaBuilder cb;

    public PersistenceManager(String persistenceUnitName) {
        emf = Persistence.createEntityManagerFactory(persistenceUnitName);
        em = emf.createEntityManager();
        cb = em.getCriteriaBuilder();
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public CriteriaBuilder getCriteriaBuilder() {
        return cb;
    }

    public void close() {
        em.close();
        emf.close();
    }
}
