package fr.eventmanager.service.impl;

import fr.eventmanager.dao.IUserDAO;
import fr.eventmanager.entity.User;
import fr.eventmanager.service.IUserService;

import java.util.Optional;

/**
 * Implémentation du service utilisateur
 *
 * @author Paul Defois
 */
public class UserService implements IUserService {
    private IUserDAO userDAO;

    public UserService(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean areCredentialsValid(String email, String password) {
        return userDAO.findByEmailAndPassword(email, password).isPresent();
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    @Override
    public boolean isUserExists(String email) {
        return userDAO.findByEmail(email).isPresent();
    }
}
