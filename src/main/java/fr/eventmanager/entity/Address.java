package fr.eventmanager.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Clément Garbay
 */
@Embeddable
@Access(AccessType.FIELD)
public class Address {
    @Column(name = "name")
    protected String name;

    @Column(name = "city")
    protected String city;

    @Column(name = "zipCode")
    protected Integer zipCode;

    @Column(name = "country")
    protected String country;

    public Address(String name, String city, Integer zipCode, String country) {
        this.name = name;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
    }

    public Address() {
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public String getCountry() {
        return country;
    }
}