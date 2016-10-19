package fr.eventmanager.dao;

import fr.eventmanager.entity.Event;

/**
 * @author Clément Garbay
 */
public interface IEventDAO extends IBasicDAO<Event> {
    boolean addParticipant(int eventId, int userId);
}
