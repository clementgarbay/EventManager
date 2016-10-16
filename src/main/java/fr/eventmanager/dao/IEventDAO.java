package fr.eventmanager.dao;

import fr.eventmanager.model.Event;

/**
 * @author Clément Garbay
 */
public interface IEventDAO extends IBasicDAO<Event> {
    boolean addParticipant(int eventId, int userId);
}
