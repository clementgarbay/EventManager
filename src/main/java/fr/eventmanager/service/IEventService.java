package fr.eventmanager.service;

import fr.eventmanager.entity.Event;

import java.util.List;
import java.util.Optional;

/**
 * @author Clément Garbay
 */
public interface IEventService {
    List<Event> getEvents();
    Optional<Event> getEvent(int eventId);
    boolean addParticipant(int eventId, int userId);
}
