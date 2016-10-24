package fr.eventmanager.entity;

import fr.eventmanager.utils.Field;
import fr.eventmanager.utils.validator.EitherValidatorResult;
import fr.eventmanager.utils.validator.ValidatableEntity;
import fr.eventmanager.utils.validator.ValidationMessage;
import fr.eventmanager.utils.validator.ValidationMessage.ErrorMessage;

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

    private static final String fieldId = "id";
    private static final String fieldTitle = "title";
    private static final String fieldDescription = "description";
    private static final String fieldDate = "date";
    private static final String fieldAddress = "address";
    private static final String fieldMaxTickets = "maxTickets";
    private static final String fieldPrice = "price";
    private static final String fieldOwner = "owner";
    private static final String fieldParticipants = "participants";

    @Id
    @GeneratedValue
    @Column(name = fieldId)
    private Integer id;

    @Column(name = fieldTitle, nullable = false)
    private String title;

    @Column(name = fieldDescription, columnDefinition = "TEXT", nullable = false)
    private String description;

    @Temporal(TemporalType.DATE)
    @Column(name = fieldDate, nullable = false)
    private Date date;

    @Embedded
    @Column(name = fieldAddress, nullable = false)
    private Address address;

    @Column(name = fieldMaxTickets, nullable = false)
    private Integer maxTickets;

    @Column(name = fieldPrice, nullable = false)
    private Double price = 0.00;

    @ManyToOne
    @JoinColumn(name = fieldOwner, nullable = false)
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
        this(title, description, date, address, maxTickets, price, owner, null);
    }

    public Event(String title, String description, Date date, Address address, Integer maxTickets, User owner) {
        this(title, description, date, address, maxTickets, 0.0, owner, null);
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

    @Override
    public List<Field> getFields() {
        return new ArrayList<Field>() {{
            add(new Field<>(fieldTitle, title));
            add(new Field<>(fieldDescription, description));
            add(new Field<>(fieldDate, date));
            add(new Field<>(fieldAddress, address));
            add(new Field<>(fieldMaxTickets, maxTickets));
            add(new Field<>(fieldPrice, price));
            add(new Field<>(fieldOwner, owner));
            add(new Field<>(fieldParticipants, participants));
        }};
    }

    @Override
    public EitherValidatorResult<Event> validate() {

        if ((isNull(title) || title.isEmpty()) ||
            (isNull(description) || description.isEmpty()) ||
            (isNull(address) || address.name.isEmpty() || address.city.isEmpty() || address.country.isEmpty()) ||
            isNull(maxTickets) ||
            isNull(price) ||
            isNull(owner)) {
            return EitherValidatorResult.error(new ValidationMessage(ErrorMessage.ARE_EMPTY));
        }

        if (isNull(date)) {
            return EitherValidatorResult.error(new ValidationMessage(ErrorMessage.IS_INCORRECT, "date & heure"));
        }

        if (String.valueOf(address.zipCode).length() > 5) {
            return EitherValidatorResult.error(new ValidationMessage(ErrorMessage.IS_INCORRECT, "code postal"));
        }

        return EitherValidatorResult.success();
    }

}
