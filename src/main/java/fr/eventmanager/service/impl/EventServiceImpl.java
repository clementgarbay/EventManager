package fr.eventmanager.service.impl;

import fr.eventmanager.dao.EventDAO;
import fr.eventmanager.model.Event;
import fr.eventmanager.service.EventService;

import java.util.List;
import java.util.Optional;

/**
 * @author Clément Garbay
 */
public class EventServiceImpl implements EventService {

    private EventDAO eventDAO;

    public EventServiceImpl(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    @Override
    public List<Event> getEvents() {
        return eventDAO.getAll();
    }

    @Override
    public Optional<Event> getEvent(int eventId) {
        return eventDAO.getById(eventId);
    }

    @Override
    public boolean addParticipant(int eventId, int userId) {
        return eventDAO.addParticipant(eventId, userId);
    }
}
