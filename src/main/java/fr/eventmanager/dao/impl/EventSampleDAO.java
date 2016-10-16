package fr.eventmanager.dao.impl;

import fr.eventmanager.dao.IEventDAO;
import fr.eventmanager.model.Event;

/**
 * @author Clément Garbay
 */
public class EventSampleDAO extends BasicDAO<Event> implements IEventDAO {

    public EventSampleDAO() {
        super("Event", Event.class);
    }

    @Override
    public boolean addParticipant(int eventId, int userId) {
        return false;
//        Optional<Event> eventOptional = getById(eventId);
//        eventOptional.ifPresent(event -> event.getParticipants().add(userId));
//        return eventOptional.isPresent();
    }
}
