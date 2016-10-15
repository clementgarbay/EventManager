package fr.eventmanager.dao.impl;

import fr.eventmanager.dao.EventDAO;
import fr.eventmanager.dao.PersistenceManager;
import fr.eventmanager.model.Event;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

/**
 * @author Clément Garbay
 */
public class EventSampleDAOImpl implements EventDAO {

    private final PersistenceManager persistenceManager = PersistenceManager.getInstance();

    public EventSampleDAOImpl() {}

    @Override
    public List<Event> getAll() {
        Query query = persistenceManager.getEntityManager().createQuery("SELECT e FROM Event e");
        return (List<Event>) query.getResultList();
    }

    @Override
    public Optional<Event> getById(int eventId) {
        Event event = persistenceManager.getEntityManager().find(Event.class, eventId);
        if (event == null) return Optional.empty();
        return Optional.of(event);
    }

    @Override
    public Event addEvent(Event event) {
        persistenceManager.getEntityManager().getTransaction().begin();
        persistenceManager.getEntityManager().persist(event);
        persistenceManager.getEntityManager().getTransaction().commit();
        return event;
    }

    @Override
    public boolean addParticipant(int eventId, int userId) {
        return false;
//        Optional<Event> eventOptional = getById(eventId);
//        eventOptional.ifPresent(event -> event.getParticipants().add(userId));
//        return eventOptional.isPresent();
    }
}
