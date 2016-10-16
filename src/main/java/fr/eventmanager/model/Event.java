package fr.eventmanager.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Clément Garbay
 */
@Entity
public class Event implements Serializable, StorableEntity<Event> {

    @Id
    @GeneratedValue
    private int id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    private Date date;
    private String postalAddress; // TODO : find a better solution to store postal address
    @OneToOne
    private User owner;
    @OneToMany
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

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Event populateFrom(Event element) {
        this.title = element.getTitle();
        this.description = element.getDescription();
        this.date = element.getDate();
        this.postalAddress = element.getPostalAddress();
        this.owner = element.getOwner();
        this.participants = element.getParticipants();
        return this;
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
