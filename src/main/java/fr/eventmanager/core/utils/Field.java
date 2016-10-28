package fr.eventmanager.core.utils;

/**
 * @author Clément Garbay
 */
public class Field<T> {

    private String name;
    private T value;

    public Field(String name, T value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public T getValue() {
        return value;
    }
}
