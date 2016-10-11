package fr.eventmanager.dao.impl;

import fr.eventmanager.dao.EventDAO;
import fr.eventmanager.model.Event;
import fr.eventmanager.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Clément Garbay
 */
public class EventDAOImpl implements EventDAO {

    private List<Event> events;

    public EventDAOImpl() {
        this.events = new ArrayList<Event>() {{
            add(new Event(1, "Gouvernance et transmission dans les entreprises familiales", "Comment la gouvernance peut-elle favoriser la transmission dans les entreprises familiales ? Quels outils pour quelle gouvernance ? Comment les outils de gouvernance peuvent permettre d’anticiper la transmission de l’entreprise ? Sophie Bellon, Présidente du Conseil d'Administration de Sodexo sera l'invitée d'honneur de cette conférence.Son intervention sera suivie d'une table ronde où témoigneront chefs d'entreprises familiales et experts pour échanger sur les différents outils facilitant l’organisation de la transmission par la gouvernance.", new Date(), "Audencia Business School, 8 route de la jonelière, 44300 Nantes", new User("email@email.com", "L'inconnu", "Inconnu"), new ArrayList<>()));
            add(new Event(2, "Le carrefour de la gouvernance - Entreprises", "NAPF, Audencia, IFA et APIA ont le plaisir de vous inviter à cette soirée exceptionnelle dédiée aux rencontres entre entreprises et administrateurs le jeudi 20 octobre à 18h30 au Château de la Gournerie.", new Date(), "Château de la Gournerie, 44800 Saint-Herblain", new User("email@email.com", "L'inconnu", "Inconnu"), new ArrayList<>()));
        }};
    }

    @Override
    public List<Event> getAll() {
        return events;
    }

    @Override
    public Optional<Event> getById(int eventId) {
        return events.stream()
                .filter(event -> event.getId() == eventId)
                .findFirst();
    }

    @Override
    public boolean addParticipant(int eventId, int userId) {
        Optional<Event> eventOptional = getById(eventId);
        eventOptional.ifPresent(event -> event.getParticipants().add(userId));
        return eventOptional.isPresent();
    }
}
