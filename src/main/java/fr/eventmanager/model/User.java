package fr.eventmanager.model;

import java.security.Principal;

/**
 * @author Clément Garbay
 */
public class User implements Principal {
    private String email; // unique id
    private String name;
    private String company;

    public User(String email, String name, String company) {
        this.email = email;
        this.name = name;
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }
}
