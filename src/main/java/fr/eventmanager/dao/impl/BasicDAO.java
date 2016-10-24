package fr.eventmanager.dao.impl;

import fr.eventmanager.dao.IBasicDAO;
import fr.eventmanager.entity.Address;
import fr.eventmanager.entity.Event;
import fr.eventmanager.entity.StorableEntity;
import fr.eventmanager.entity.User;
import fr.eventmanager.utils.persistence.BaseQuery;
import fr.eventmanager.utils.persistence.DatabaseManager;
import fr.eventmanager.utils.persistence.QueryField;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import java.util.*;

/**
 * Provides CRUD methods on a specific entity.
 *
 * @author Clément Garbay
 */
public class BasicDAO<T extends StorableEntity> extends DatabaseManager<T> implements IBasicDAO<T> {

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
        BaseQuery<T> baseQuery = getBaseQuery(Action.READ);
        CriteriaQuery<T> abstractCriteria = ((CriteriaQuery<T>) baseQuery.getAbstractCriteria())
                .select(baseQuery.getEntity());
        return getResultList(entityManager.createQuery(abstractCriteria));
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
        User userClement = new User("Clément", "clementgarbay@gmail.com", "garbay", "Dictanova");
        User userElie = new User("Elie", "elie.gourdeau@gmail.com", "gourdeau", "Orange");
        User userPaul = new User("Paul", "paul.defois@gmail.com", "defois", "Kosmos");

        Event event1 = new Event("Gouvernance et transmission dans les entreprises familiales",  "Comment la gouvernance peut-elle favoriser la transmission dans les entreprises familiales ? Quels outils pour quelle gouvernance ? Comment les outils de gouvernance peuvent permettre d’anticiper la transmission de l’entreprise ? Sophie Bellon, Présidente du Conseil d'Administration de Sodexo sera l'invitée d'honneur de cette conférence.Son intervention sera suivie d'une table ronde où témoigneront chefs d'entreprises familiales et experts pour échanger sur les différents outils facilitant l’organisation de la transmission par la gouvernance.", randomDate(), new Address("Audencia Business School, 8 route de la jonelière", "Nantes", 44300, "France"), 50, userClement);
        Event event2 = new Event("Le carrefour de la gouvernance - Entreprises",  "NAPF, Audencia, IFA et APIA ont le plaisir de vous inviter à cette soirée exceptionnelle dédiée aux rencontres entre entreprises et administrateurs le jeudi 20 octobre à 18h30 au Château de la Gournerie.", randomDate(), new Address("Château de la Gournerie", "Saint-Herblain", 44800, "France"), 35, userElie);
        Event event3 = new Event("Formation Zoho CRM NANTES",  "Cette formation s'adresse à toute personne souhaitant avoir les bons réflexes pour acquérir les bonnes pratiques d'utilisation pour les commerciaux (atelier du matin), jusqu'à l'optimisation sa base Zoho CRM, depuis l'adaptation du paramétrage standard au métier de l'entreprise (atelier de l'après-midi).", randomDate(), new Address("BIOBURO, 14 rue François Evellin", "Nantes", 44000, "France"), 45, userPaul);

        begin();

        Arrays.asList(userClement, userElie, userPaul, event1, event2, event3).forEach(element -> entityManager.persist(element));

        commit();
    }

    private Date randomDate() {
        return new Date((long) (1293861599 + new Random().nextDouble() * 60 * 60 * 24 * 365));
    }

}
