package fr.eventmanager.controller;

import fr.eventmanager.dao.impl.EventSampleDAOImpl;
import fr.eventmanager.service.EventService;
import fr.eventmanager.service.impl.EventServiceImpl;
import fr.eventmanager.utils.Alert;
import fr.eventmanager.utils.HttpMethod;
import fr.eventmanager.utils.router.Route;
import fr.eventmanager.utils.router.ServletRouter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * @author Clément Garbay
 */
public class EventsServlet extends Servlet {

    private EventService eventService;

    @Override
    public void init() throws ServletException {
        super.init();

        // TODO : use injection dependency
        this.eventService = new EventServiceImpl(new EventSampleDAOImpl());

        super.servletRouter = new ServletRouter(this)
                .registerRoute(HttpMethod.GET, new Route(Pattern.compile("/"), "getEvents", false))
                .registerRoute(HttpMethod.GET, new Route(Pattern.compile("/(?<eventId>\\d+)"), "getEvent", false))
                .registerRoute(HttpMethod.POST, new Route(Pattern.compile("/(?<eventId>\\d+)"), "addEvent", true));
    }

    private void getEvents(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        render("events.jsp", request, response);
    }

    private void getEvent(HttpServletRequest request,  HttpServletResponse response, Map<String, String> parameters) throws ServletException, IOException {
        int eventId = Integer.parseInt(parameters.get("eventId"));

        System.out.println(eventId);

        render("event.jsp", request, response);
    }

    private void addEvent(HttpServletRequest request,  HttpServletResponse response, Map<String, String> parameters) throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String confirmEmail = request.getParameter("confirm_email");

        int eventId = Integer.parseInt(parameters.get("eventId"));

        // TODO : check if a user is connected or if the user already exists

        if (!name.isEmpty() && !email.isEmpty() && !confirmEmail.isEmpty()) {
            if (Objects.equals(email, confirmEmail)) {

                // eventService.addParticipant()

                render("event.jsp", request, response, new Alert(Alert.AlertType.SUCCESS, "Inscription validée. Vous allez recevoir un email de confirmation."));
            } else {
                render("event.jsp", request, response, new Alert(Alert.AlertType.DANGER, "Les emails doivent être identiques."));
            }
        } else {
            render("event.jsp", request, response, new Alert(Alert.AlertType.DANGER, "Tous les champs doivent être remplis."));
        }
    }


}
