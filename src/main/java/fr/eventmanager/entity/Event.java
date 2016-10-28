package fr.eventmanager.entity;

import fr.eventmanager.core.utils.PreparedMessage;
import fr.eventmanager.core.validator.EitherValidatorResult;
import fr.eventmanager.core.validator.ValidatableEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.Objects.isNull;

/**
 * @author Clément Garbay
 */
@Entity
@Table(name = Event.tableName)
public class Event implements Serializable, StorableEntity, ValidatableEntity {

    static final String tableName = "Event";

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;

    @Temporal(TemporalType.DATE)
    @Column(name = "date", nullable = false)
    private Date date;

    @Embedded
    @Column(name = "address", nullable = false)
    private Address address;

    @Column(name = "maxTickets", nullable = false)
    private Integer maxTickets;

    @Column(name = "price", nullable = false)
    private Double price = 0.00;

    @ManyToOne
    @JoinColumn(name = "owner", nullable = false)
    private User owner;

    @ManyToMany
    @JoinTable(name = "event_user",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> participants;

    public Event(String title, String description, Date date, Address address, Integer maxTickets, Double price, User owner, List<User> participants) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.address = address;
        this.maxTickets = maxTickets;
        this.price = price;
        this.owner = owner;
        this.participants = participants;
    }

    public Event(String title, String description, Date date, Address address, Integer maxTickets, Double price, User owner) {
        this(title, description, date, address, maxTickets, price, owner, new ArrayList<>());
    }

    public Event(String title, String description, Date date, Address address, Integer maxTickets, User owner) {
        this(title, description, date, address, maxTickets, 0.0, owner, new ArrayList<>());
    }

    public Event() {}

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
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

    public Address getAddress() {
        return address;
    }

    public Integer getMaxTickets() {
        return maxTickets;
    }

    public Double getPrice() {
        return price;
    }

    public User getOwner() {
        return owner;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public Event addParticipant(User user) {
        if (isNull(participants)) participants = new ArrayList<>();
        participants.add(user);
        return this;
    }

    public Event removeParticipant(User user) {
        if (!isNull(participants)) {
            participants.removeIf(participant -> participant.getId().equals(user.getId()));
        }
        return this;
    }

    public boolean isOwner(User user) {
        return !isNull(this.owner) && this.owner.getId().equals(user.getId());
    }

    public boolean isParticipant(User user) {
        return participants.stream().anyMatch(u -> u.getId().equals(user.getId()));
    }

    @Override
    public EitherValidatorResult validate() {

        if ((isNull(title) || title.isEmpty()) ||
            (isNull(description) || description.isEmpty()) ||
            (isNull(address) || isNull(address.zipCode) || address.name.isEmpty() || address.city.isEmpty() || address.country.isEmpty()) ||
            isNull(maxTickets) ||
            isNull(price) ||
            isNull(owner)) {
            return EitherValidatorResult.error(PreparedMessage.VALIDATION_ARE_EMPTY.getMessage());
        }

        if (isNull(date)) {
            return EitherValidatorResult.error(PreparedMessage.VALIDATION_IS_INCORRECT.getMessage("\"Date & heure\""));
        }

        if (String.valueOf(address.zipCode).length() != 5) {
            return EitherValidatorResult.error(PreparedMessage.VALIDATION_IS_INCORRECT.getMessage("\"Code postal\""));
        }

        return EitherValidatorResult.success();
    }

}
