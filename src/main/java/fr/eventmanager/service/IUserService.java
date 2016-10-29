package fr.eventmanager.service;

import fr.eventmanager.entity.User;

import java.util.Optional;

/**
 * @author Paul Defois
 */
public interface IUserService extends IService {
    boolean register(User user);
    Optional<User> getUserByEmail(String email);
    boolean areCredentialsValid(String email, String password);
}
