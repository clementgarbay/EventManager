package fr.eventmanager.dao;

import fr.eventmanager.model.Event;

import java.util.List;
import java.util.Optional;

/**
 * @author Clément Garbay
 */
public interface EventDAO {
    List<Event> getAll();
    Optional<Event> getById(int eventId);
    Event addEvent(Event event);
    boolean addParticipant(int eventId, int userId);
}
