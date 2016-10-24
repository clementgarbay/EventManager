package fr.eventmanager.dao.impl;

import fr.eventmanager.dao.IEventDAO;
import fr.eventmanager.entity.Event;
import fr.eventmanager.entity.User;
import fr.eventmanager.utils.persistence.QueryField;

import java.util.List;

/**
 * @author Clément Garbay
 */
public class EventDAO extends BasicDAO<Event> implements IEventDAO {

    public EventDAO() {
        super();
    }

    @Override
    public List<Event> findByOwner(User user) {
        return findListByFields(new QueryField<>("owner", user));
    }

    @Override
    public List<Event> findByParticipant(User user) {
        return null;
    }

    @Override
    public boolean addParticipant(int eventId, int userId) {
        return false;
//        Optional<Event> eventOptional = getById(eventId);
//        eventOptional.ifPresent(event -> event.getParticipants().add(userId));
//        return eventOptional.isPresent();
    }

}
