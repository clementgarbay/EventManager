package fr.eventmanager.service;

import fr.eventmanager.entity.Event;
import fr.eventmanager.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * @author Clément Garbay
 */
public interface IEventService extends IService {
    List<Event> getEvents();
    Optional<Event> getEvent(int eventId);
    boolean addEvent(Event event);
    boolean updateEvent(Event event);
    boolean subscribe(Event event, User user);
    boolean unsubscribe(Event event, User user);
}
