package fr.eventmanager.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Clément Garbay
 */
@Entity
public class User implements Serializable, StorableEntity {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String email;
    private String password;
    private String company;
    private boolean connected;

    public User(String name, String email, String password, String company) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.company = company;
        this.connected = false;
    }

    public User(String email, String name, String company) {
        this(email, name, null, company);
    }

    public User(String email, String name) {
        this(email, name, null);
    }

    public User() {}

    public static String getTableName() {
        return "User";
    }

    @Override
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
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
