package fr.eventmanager.dao.impl;

import fr.eventmanager.dao.IUserDAO;
import fr.eventmanager.entity.User;
import fr.eventmanager.utils.persistence.QueryField;

import java.util.Optional;

/**
 * @author Clément Garbay
 * @author Paul Defois
 */
public class UserDAO extends BasicDAO<User> implements IUserDAO {

    public Optional<User> findByEmail(String email) {
        return findSingleByFields(new QueryField<>("email", email));
    }

    public Optional<User> findByEmailAndPassword(String email, String password) {
        return findSingleByFields(new QueryField<>("email", email), new QueryField<>("password", password));
    }

}
