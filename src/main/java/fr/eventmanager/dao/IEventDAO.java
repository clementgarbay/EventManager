package fr.eventmanager.dao;

import fr.eventmanager.entity.Event;
import fr.eventmanager.entity.User;

import java.util.List;

/**
 * @author Clément Garbay
 */
public interface IEventDAO extends IBasicDAO<Event> {
    List<Event> findByOwner(User user);
    List<Event> findByParticipant(User user);
    boolean addParticipant(int eventId, int userId);
}
