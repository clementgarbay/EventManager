package fr.eventmanager.model;

/**
 * @author Clément Garbay
 */
public interface StorableEntity<T> {
    int getId();
    T populateFrom(T element);
}
