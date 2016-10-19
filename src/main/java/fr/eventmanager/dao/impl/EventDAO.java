package fr.eventmanager.dao.impl;

import fr.eventmanager.dao.IEventDAO;
import fr.eventmanager.entity.Event;

/**
 * @author Clément Garbay
 */
public class EventDAO extends BasicDAO<Event> implements IEventDAO {

    public EventDAO() {
        super();
    }

    @Override
    public boolean addParticipant(int eventId, int userId) {
        return false;
//        Optional<Event> eventOptional = getById(eventId);
//        eventOptional.ifPresent(event -> event.getParticipants().add(userId));
//        return eventOptional.isPresent();
    }

}
