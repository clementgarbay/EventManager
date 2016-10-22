package fr.eventmanager.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Clément Garbay
 */
@Entity(name = User.tableName)
public class User implements Serializable, StorableEntity {

    static final String tableName = "User";

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "company", nullable = false)
    private String company;

    @Column(name = "registered", nullable = false)
    private boolean registered = false;

    public User(String name, String email, String password, String company, boolean registered) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.company = company;
        this.registered = registered;
    }

    /**
     * Constructor corresponding to an user registered by the signup form
     */
    public User(String name, String email, String password, String company) {
        this(name, email, password, company, true);
    }

    /**
     * Constructor corresponding to an participant user to an event (filled with the event's form) with no registered account
     */
    public User(String name, String email, String company) {
        this(name, email, null, company, false);
    }

    public User() {}

    @Override
    public Integer getId() {
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

    public boolean isRegistered() {
        return registered;
    }
}
