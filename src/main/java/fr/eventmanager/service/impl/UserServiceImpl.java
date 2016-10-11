package fr.eventmanager.service.impl;

import fr.eventmanager.dao.UserDAO;
import fr.eventmanager.model.User;
import fr.eventmanager.service.UserService;

/**
 * Implémentation du service utilisateur
 *
 * @author Paul Defois
 */
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean areCredentialsValid(String email, String password) {
        return userDAO.areCredentialsValid(email, password);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }

    @Override
    public boolean isUserExists(String email) {
        return userDAO.isUserExists(email);
    }
}
