package fr.eventmanager.dao.impl;

import fr.eventmanager.dao.IEventDAO;
import fr.eventmanager.entity.Event;
import fr.eventmanager.entity.User;
import fr.eventmanager.utils.persistence.BaseQuery;
import fr.eventmanager.utils.persistence.QueryField;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * @author Clément Garbay
 */
public class EventDAO extends BasicDAO<Event> implements IEventDAO {

    @Override
    public List<Event> findByOwner(User user) {
        return findListByFields(new QueryField<>("owner", user));
    }

    @Override
    public List<Event> findByParticipant(User user) {
        BaseQuery<Event, CriteriaQuery<Event>> baseQuery = getBaseQuery(Action.READ);
        CriteriaQuery<Event> criteriaQuery = baseQuery.getAbstractCriteria()
                .where(super.criteriaBuilder.isMember(user, baseQuery.getEntity().get("participants")));

        return getResultList(entityManager.createQuery(criteriaQuery));
    }

    @Override
    public boolean subscribe(Event event, User user) {
        event.addParticipant(user);
        return update(event);
    }

    @Override
    public boolean unsubscribe(Event event, User user) {
        event.removeParticipant(user);
        return update(event);
    }
}
