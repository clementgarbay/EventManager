package fr.eventmanager.dao.impl;

import fr.eventmanager.dao.IUserDAO;
import fr.eventmanager.entity.Event;
import fr.eventmanager.entity.User;

import javax.persistence.Query;
import java.util.Optional;

/**
 * @author Paul Defois
 */
public class UserDAO extends BasicDAO<User> implements IUserDAO {

    public UserDAO() {
        super();
    }

    public Optional<User> findByEmail(String email) {
        Query query = persistenceManager.getEntityManager().createQuery(String.format("SELECT name, email, company FROM %s WHERE email=%s", super.tableName, email));
        return Optional.ofNullable((User) query.getSingleResult());
    }

    public Optional<User> findByEmailAndPassword(String email, String password) {
        Query query = persistenceManager.getEntityManager().createQuery(String.format("SELECT name, email, company FROM %s WHERE email=%s AND password=%s", super.tableName, email, password));
        return Optional.ofNullable((User) query.getSingleResult());
    }

}
