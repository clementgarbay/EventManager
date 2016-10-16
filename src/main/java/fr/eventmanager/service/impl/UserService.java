package fr.eventmanager.service.impl;

import fr.eventmanager.dao.IUserDAO;
import fr.eventmanager.model.User;
import fr.eventmanager.service.IUserService;

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
