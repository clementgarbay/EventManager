package fr.eventmanager.dao;

import fr.eventmanager.model.User;

/**
 * Created by Polo on 11/10/2016.
 */
public interface UserDAO {
    boolean areCredentialsValid(String email, String password);
    User getUserByEmail(String email);
    boolean isUserExists(String email);
}
