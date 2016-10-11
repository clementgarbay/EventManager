package fr.eventmanager.dao.impl;

import fr.eventmanager.dao.UserDAO;
import fr.eventmanager.model.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Polo on 11/10/2016.
 */
public class UserSampleDAOImpl implements UserDAO {
    private Map<String, String> credentials;
    private Map<String, User> users;

    public UserSampleDAOImpl() {
        this.credentials = new HashMap<>();
        this.users = new HashMap<>();

        this.users.put("clement.garbay@gmail.com", new User("clement.garbay@gmail.com", "Clément", "Dictanova"));
        this.users.put("elie.gourdeau@gmail.com", new User("elie.gourdeau@gmail.com", "Elie", "Orange"));
        this.users.put("paul.defois@gmail.com", new User("paul.defois@gmail.com", "Paul", "Kosmos"));

        this.credentials.put("clement.garbay@gmail.com", "garbay");
        this.credentials.put("elie.gourdeau@gmail.com", "gourdeau");
        this.credentials.put("paul.defois@gmail.com", "defois");
    }

    @Override
    public boolean areCredentialsValid(String email, String password) {
        return isUserExists(email) && this.credentials.get(email).equals(password);
    }

    @Override
    public User getUserByEmail(String email) {
        return this.users.get(email);
    }

    @Override
    public boolean isUserExists(String email) {
        return this.users.get(email) != null;
    }
}
