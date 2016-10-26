package fr.eventmanager.service.impl;

import fr.eventmanager.dao.IEventDAO;
import fr.eventmanager.entity.Event;
import fr.eventmanager.entity.User;
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
        return eventDAO.findById(eventId);
    }

    @Override
    public boolean addEvent(Event event) {
        return eventDAO.create(event);
    }

    @Override
    public boolean subscribe(Event event, User user) {
        return eventDAO.subscribe(event, user);
    }

    @Override
    public boolean unsubscribe(Event event, User user) {
        return eventDAO.unsubscribe(event, user);
    }

    @Override
    public boolean updateEvent(Event event) {
        return eventDAO.update(event);
    }


    @Override
    public void close() {
        eventDAO.close();
    }
}
