package fr.eventmanager.model;

import java.util.Date;
import java.util.UUID;

/**
 * @author Clément Garbay
 */
public class Event {
    private UUID id;
    private String title;
    private String description;
    private User owner;
    private Date date;
    private String postalAddress; // TODO : find a better solution to store postal address

    public Event(UUID id, String title, String description, User owner, Date date, String postalAddress) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.owner = owner;
        this.date = date;
        this.postalAddress = postalAddress;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public User getOwner() {
        return owner;
    }

    public Date getDate() {
        return date;
    }

    public String getPostalAddress() {
        return postalAddress;
    }
}
