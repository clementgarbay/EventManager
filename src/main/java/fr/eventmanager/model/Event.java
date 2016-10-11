package fr.eventmanager.model;

import java.util.Date;
import java.util.List;

/**
 * @author Clément Garbay
 */
public class Event {
    private int id;
    private String title;
    private String description;
    private Date date;
    private String postalAddress; // TODO : find a better solution to store postal address
    private User owner;
    private List<Integer> participantsIds;

    public Event(int id, String title, String description, Date date, String postalAddress, User owner, List<Integer> participantsIds) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.postalAddress = postalAddress;
        this.owner = owner;
        this.participantsIds = participantsIds;
    }

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

    public List<Integer> getParticipants() {
        return participantsIds;
    }
}
