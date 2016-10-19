package fr.eventmanager.service;

import fr.eventmanager.entity.User;

import java.util.Optional;

/**
 * Created by Polo on 11/10/2016.
 */
public interface IUserService {
    Optional<User> getUserByEmail(String email);
    boolean isUserExists(String email);
    boolean areCredentialsValid(String email, String password);
}
