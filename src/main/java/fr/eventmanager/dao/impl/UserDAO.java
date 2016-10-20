package fr.eventmanager.dao.impl;

import fr.eventmanager.dao.DbField;
import fr.eventmanager.dao.IUserDAO;
import fr.eventmanager.entity.Event;
import fr.eventmanager.entity.User;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import java.text.Format;
import java.util.Optional;

/**
 * @author Clément Garbay
 * @author Paul Defois
 */
public class UserDAO extends BasicDAO<User> implements IUserDAO {

    public UserDAO() {
        super();
    }

    public Optional<User> findByEmail(String email) {
        return findByFields(new DbField("email", email));
    }

    public Optional<User> findByEmailAndPassword(String email, String password) {
        Optional<User> userOptional = findByFields(new DbField("email", email), new DbField("password", password));
        return userOptional;
    }

}
