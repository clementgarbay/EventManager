package fr.eventmanager.service;

import fr.eventmanager.entity.User;

import java.util.Optional;

/**
 * @author Paul Defois
 */
public interface IUserService {
    Optional<User> getUserByEmail(String email);
    boolean isUserExists(String email);
    boolean areCredentialsValid(String email, String password);
}
