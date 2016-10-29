package fr.eventmanager.dao.impl;

import fr.eventmanager.core.persistence.BaseQuery;
import fr.eventmanager.core.persistence.PersistenceManager;
import fr.eventmanager.core.persistence.QueryField;
import fr.eventmanager.dao.IBasicDAO;
import fr.eventmanager.entity.Address;
import fr.eventmanager.entity.Event;
import fr.eventmanager.entity.StorableEntity;
import fr.eventmanager.entity.User;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

/**
 * Provides CRUD methods on a specific entity.
 *
 * @author Clément Garbay
 */
public class BasicDAO<T extends StorableEntity> extends PersistenceManager<T> implements IBasicDAO<T> {

    public BasicDAO() {
        super("EventManagerUnit");

        populateWithDummyData();
    }

    @Override
    public Optional<T> findById(int id) {
        return findSingleByFields(new QueryField<>("id", id));
    }

    @Override
    public Optional<T> findSingleByFields(QueryField... fields) {
        Query query = getReadQueryBy(fields);
        return Optional.ofNullable(getSingleResult(query));
    }

    @Override
    public List<T> findAll() {
        BaseQuery<T, CriteriaQuery<T>> baseQuery = getBaseQuery(Action.READ);
        CriteriaQuery<T> criteriaQuery = baseQuery.getAbstractCriteria()
                .select(baseQuery.getEntity());
        return getResultList(entityManager.createQuery(criteriaQuery));
    }

    @Override
    public List<T> findListByFields(QueryField... fields) {
        Query query = getReadQueryBy(fields);
        return getResultList(query);
    }

    @Override
    public boolean create(T element) {
        return proceedToQuery(entityManager -> entityManager.persist(element));
    }

    @Override
    public boolean update(T element) {
        Optional<T> elementOptional = findById(element.getId());
        return  elementOptional.isPresent() &&
                proceedToQuery(entityManager -> entityManager.merge(element));
    }

    @Override
    public boolean delete(int id) {
        Optional<T> elementOptional = findById(id);
        return  elementOptional.isPresent() &&
                proceedToQuery(entityManager -> entityManager.remove(elementOptional.get()));
    }

    private void populateWithDummyData() {
        User userClement = new User("Clément Garbay", "clementgarbay@gmail.com", "garbay", "Dictanova");
        User userElie = new User("Elie Gourdeau", "elie.gourdeau@gmail.com", "gourdeau", "Orange");
        User userPaul = new User("Paul Defois", "paul.defois@gmail.com", "defois", "Kosmos");
        User userMuddy = new User("Muddy Angel", "muddyangel@gmail.com", "muddy", "Muddy");

        Event event1 = new Event("Gouvernance et transmission dans les entreprises familiales",  "Comment la gouvernance peut-elle favoriser la transmission dans les entreprises familiales ? Quels outils pour quelle gouvernance ?Comment les outils de gouvernance peuvent permettre d’anticiper la transmission de l’entreprise ?\nSophie Bellon, Présidente du Conseil d'Administration de Sodexo sera l'invitée d'honneur de cette conférence.Son intervention sera suivie d'une table ronde où témoigneront chefs d'entreprises familiales et experts pour échanger sur les différents outils facilitant l’organisation de la transmission par la gouvernance.", buildDate(2016, Calendar.NOVEMBER, 22, 14, 30), new Address("Audencia Business School, 8 route de la jonelière", "Nantes", 44300, "France"), 50, userClement);
        Event event2 = new Event("Le carrefour de la gouvernance - Entreprises",  "NAPF, Audencia, IFA et APIA ont le plaisir de vous inviter à cette soirée exceptionnelle dédiée aux rencontres entre entreprises et administrateurs le jeudi 20 octobre à 18h30 au Château de la Gournerie.", buildDate(2016, Calendar.NOVEMBER, 12, 10, 30), new Address("Château de la Gournerie", "Saint-Herblain", 44800, "France"), 1, userElie);
        Event event3 = new Event("Formation Zoho CRM NANTES",  "Cette formation s'adresse à toute personne souhaitant avoir les bons réflexes pour acquérir les bonnes pratiques d'utilisation pour les commerciaux (atelier du matin), jusqu'à l'optimisation sa base Zoho CRM, depuis l'adaptation du paramétrage standard au métier de l'entreprise (atelier de l'après-midi).", buildDate(2016, Calendar.DECEMBER, 2, 16, 45), new Address("BIOBURO, 14 rue François Evellin", "Nantes", 44000, "France"), 45, userPaul);
        Event event4 = new Event("Muddy Angel Run 2017",  "QUOI: La course „Muddy Angel“, qui s’étend sur 5km, est la première course dans la boue (Mud Run) en Europe,  destinée exclusivement à toutes les femmes. Muddy Angel s'adresse aux femmes de toute condition physique confondue, désirant faire une bonne action, et y prendre du plaisir.\nQue tu participes en marchant, courant ou en te baladant, que tu sois jeune ou vieux, que tu fasses un don important ou modeste: n’hésite pas à candidater avec d’autres Anges de ton cercle d’amis et embarque ta soeur, ta mère, tes filles, tes voisines ou tes collègues avec toi, pour une bonne action et un bon moment partagé!\nQu’importe ta condition physique, ton âge ou ton parcours sportif personnel, tu passeras une journée inoubliable, boueuse et amusante avec tes amies.\nTu peux gérer les 5km selon ton propre rythme – que ce soit en marchant, en courant ou en te baladant. Il n’y a pas de chronométrage et dès le moment où tu as commencé ta course, tu fais déjà partie des „Muddy Angels“.\nPOURQUOI: La plupart des Muddy Angels ne courent pas pour elles-même mais pour une amie ou une connaissance, qui se bat ou s’est battu contre le cancer du sein, afin d’apporter un soutien moral à l’autre et ouvrir la voie.\nCOMBIEN: Pour chaque événement il y a un nombre de 300 tickets avec un prix de 29€. Le prix des tickets va augmenter chaque mois jusqu'à 59 EUR par personne au moment de l'événement. Il est donc avantageux pour vous de réserver à l’avance et cela nous aide en outre dans la planification. Tu as également la possibilité (optionnel) de faire un don entre 5 et 100 euros en plus du prix du ticket. Nous allons transférer le montant du don directement à notre partenaire: « L’Association Le Cancer du sein, Parlons-en ! »", buildDate(2017, Calendar.JUNE, 3, 20, 0), new Address("", "Nantes", 44000, "France"), 45, 35.5, userMuddy);

        begin();

        Arrays.asList(userClement, userElie, userPaul, userMuddy, event1, event2, event3, event4).forEach(element -> entityManager.persist(element));

        commit();
    }

    private Date buildDate(int year, int month, int day, int hour, int minute) {
        return Date.from(LocalDateTime.of(year, month, day, hour, minute).toInstant(ZoneOffset.UTC));
//        return new Date((long) (1293861599 + new Random().nextDouble() * 60 * 60 * 24 * 365));
    }

}
