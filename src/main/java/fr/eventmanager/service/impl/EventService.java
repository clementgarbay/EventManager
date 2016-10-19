package fr.eventmanager.service.impl;

import fr.eventmanager.dao.IEventDAO;
import fr.eventmanager.entity.Event;
import fr.eventmanager.service.IEventService;

import java.util.List;
import java.util.Optional;

/**
 * @author Clément Garbay
 */
public class EventService implements IEventService {

    private IEventDAO eventDAO;

    public EventService(IEventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    @Override
    public List<Event> getEvents() {
        return eventDAO.findAll();
    }

    @Override
    public Optional<Event> getEvent(int eventId) {
        return eventDAO.find(eventId);
    }

    @Override
    public boolean addParticipant(int eventId, int userId) {
        return eventDAO.addParticipant(eventId, userId);
    }
}
