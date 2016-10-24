package fr.eventmanager.entity;

import fr.eventmanager.utils.Field;
import fr.eventmanager.utils.validator.EitherValidatorResult;
import fr.eventmanager.utils.validator.ValidatableEntity;
import fr.eventmanager.utils.validator.ValidationMessage;
import fr.eventmanager.utils.validator.ValidationMessage.ErrorMessage;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

/**
 * @author Clément Garbay
 */
@Entity(name = User.tableName)
public class User implements Serializable, StorableEntity, ValidatableEntity {

    static final String tableName = "User";

    private static final String fieldId = "id";
    private static final String fieldName = "name";
    private static final String fieldEmail = "email";
    private static final String fieldPassword = "password";
    private static final String fieldCompany = "company";
    private static final String fieldRegistered = "registered";

    @Id
    @GeneratedValue
    @Column(name = fieldId)
    private Integer id;

    @Column(name = fieldName, nullable = false)
    private String name;

    @Column(name = fieldEmail, nullable = false, unique = true)
    private String email;

    @Column(name = fieldPassword)
    private String password;

    @Column(name = fieldCompany, nullable = false)
    private String company;

    @Column(name = fieldRegistered, nullable = false)
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

    @Override
    public List<Field> getFields() {
        return new ArrayList<Field>() {{
            add(new Field<>(fieldName, name));
            add(new Field<>(fieldEmail, email));
            add(new Field<>(fieldPassword, password));
            add(new Field<>(fieldCompany, company));
            add(new Field<>(fieldRegistered, registered));
        }};
    }

    @Override
    public EitherValidatorResult validate() {

        if ((isNull(name) || name.isEmpty()) ||
            (isNull(email) || email.isEmpty()) ||
            (isNull(password) || password.isEmpty())) {
            return EitherValidatorResult.error(this, new ValidationMessage(ErrorMessage.ARE_EMPTY));
        }

        if (String.valueOf(password).length() > 8) {
            return EitherValidatorResult.error(this, new ValidationMessage("Le mot de passe est trop court."));
        }

        return EitherValidatorResult.success(this);
    }
}
