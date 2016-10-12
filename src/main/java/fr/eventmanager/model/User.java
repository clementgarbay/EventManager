package fr.eventmanager.model;

import java.security.Principal;

/**
 * @author Clément Garbay
 */
public class User implements Principal {
    private String email; // unique id
    private String name;
    private String company;
    private boolean connected;

    public User(String email, String name, String company) {
        this.email = email;
        this.name = name;
        this.company = company;
        this.connected = false;
    }

    public User(String email, String name) {
        this(email, name, "");
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

    public boolean getConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }
}
