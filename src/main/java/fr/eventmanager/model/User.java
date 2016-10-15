package fr.eventmanager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Clément Garbay
 */
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String email; // unique id
    private String name;
    private String password;
    private String company;

    public User(String email, String name, String password, String company) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.company = company;
    }

    public User(String email, String name, String company) {
        this(email, name, null, company);
    }

    public User(String email, String name) {
        this(email, name, null);
    }

    public User() {}

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
