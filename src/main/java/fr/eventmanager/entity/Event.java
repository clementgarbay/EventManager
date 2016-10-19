package fr.eventmanager.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Clément Garbay
 */
@Entity
@Table(name = "Event")
public class Event extends StorableEntity implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;
    @Column(name = "date", nullable = false)
    private Date date;
    @Column(name = "postalAddress", nullable = false)
    private String postalAddress; // TODO : find a better solution to store postal address
    @OneToOne
    @Column(name = "owner", nullable = false)
    private User owner;
    @OneToMany
    @Column(name = "participants")
    private List<User> participants;

    public Event(String title, String description, Date date, String postalAddress, User owner, List<User> participants) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.postalAddress = postalAddress;
        this.owner = owner;
        this.participants = participants;
    }

    public Event(String title, String description, Date date, String postalAddress, User owner) {
        this(title, description, date, postalAddress, owner, null);
    }

    public Event() {}

    public static String getTableName() {
        return "Event";
    }

    @Override
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public User getOwner() {
        return owner;
    }

    public List<User> getParticipants() {
        return participants;
    }

}
