package fr.eventmanager.dao;

import fr.eventmanager.entity.Event;
import fr.eventmanager.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;


/**
 * @author Clément Garbay
 */
public class PersistenceManager {

    private static PersistenceManager INSTANCE = null;

    private EntityManagerFactory emf;
    private EntityManager em;

    private PersistenceManager() {
        emf = Persistence.createEntityManagerFactory("EventManagerUnit");
        em = emf.createEntityManager();

        populate();
    }

    public static synchronized PersistenceManager getInstance() {
        if (INSTANCE == null) INSTANCE = new PersistenceManager();
        return INSTANCE;
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public void close() {
        em.close();
        emf.close();
    }

    private void populate() {
        User userClement = new User("Clément", "clementgarbay@gmail.com", "garbay", "Dictanova");
        User userElie = new User("Elie", "elie.gourdeau@gmail.com", "gourdeau", "Orange");
        User userPaul = new User("Paul", "paul.defois@gmail.com", "defois", "Kosmos");

        Event event1 = new Event("Gouvernance et transmission dans les entreprises familiales", "Comment la gouvernance peut-elle favoriser la transmission dans les entreprises familiales ? Quels outils pour quelle gouvernance ? Comment les outils de gouvernance peuvent permettre d’anticiper la transmission de l’entreprise ? Sophie Bellon, Présidente du Conseil d'Administration de Sodexo sera l'invitée d'honneur de cette conférence.Son intervention sera suivie d'une table ronde où témoigneront chefs d'entreprises familiales et experts pour échanger sur les différents outils facilitant l’organisation de la transmission par la gouvernance.", new Date(), "Audencia Business School, 8 route de la jonelière, 44300 Nantes", userClement);
        Event event2 = new Event("Le carrefour de la gouvernance - Entreprises", "NAPF, Audencia, IFA et APIA ont le plaisir de vous inviter à cette soirée exceptionnelle dédiée aux rencontres entre entreprises et administrateurs le jeudi 20 octobre à 18h30 au Château de la Gournerie.", new Date(), "Château de la Gournerie, 44800 Saint-Herblain", userElie);
        Event event3 = new Event("Formation Zoho CRM NANTES", "Cette formation s'adresse à toute personne souhaitant avoir les bons réflexes pour acquérir les bonnes pratiques d'utilisation pour les commerciaux (atelier du matin), jusqu'à l'optimisation sa base Zoho CRM, depuis l'adaptation du paramétrage standard au métier de l'entreprise (atelier de l'après-midi).", new Date(), "BIOBURO, 14 rue François Evellin, 44000 Nantes", userPaul);

        em.getTransaction().begin();

        em.persist(userClement);
        em.persist(userElie);
        em.persist(userPaul);
        em.persist(event1);
        em.persist(event2);
        em.persist(event3);

        em.getTransaction().commit();
    }
}
