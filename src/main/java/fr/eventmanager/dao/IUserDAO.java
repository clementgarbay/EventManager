package fr.eventmanager.dao;

import fr.eventmanager.entity.User;

import java.util.Optional;

/**
 * @author Paul Defois
 */
public interface IUserDAO extends IBasicDAO<User> {
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);
}
