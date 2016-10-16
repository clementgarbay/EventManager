package fr.eventmanager.controller;

import fr.eventmanager.dao.impl.EventSampleDAO;
import fr.eventmanager.model.Event;
import fr.eventmanager.service.IEventService;
import fr.eventmanager.service.impl.EventService;
import fr.eventmanager.utils.Alert;
import fr.eventmanager.utils.HttpMethod;
import fr.eventmanager.utils.router.Route;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * @author Clément Garbay
 * @author Paul Defois
 */
@WebServlet(name = "EventServlet", urlPatterns = {EventsServlet.ROUTE_BASE + "/*"})
public class EventsServlet extends Servlet {

    static final String ROUTE_BASE = "/events";

    private IEventService eventService;

    @Override
    public void init() throws ServletException {
        super.init();

        // TODO : use injection dependency
        this.eventService = new EventService(new EventSampleDAO());

        registerRoute(HttpMethod.GET, new Route(ROUTE_BASE, Pattern.compile("/"), "getEvents", false));
        registerRoute(HttpMethod.GET, new Route(ROUTE_BASE, Pattern.compile("/(?<eventId>\\d+)"), "getEvent", false));
        registerRoute(HttpMethod.POST, new Route(ROUTE_BASE, Pattern.compile("/(?<eventId>\\d+)"), "addParticipant", true));
    }

    private void getEvents(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("events", eventService.getEvents());
        render("events.jsp", request, response);
    }

    private void getEvent(HttpServletRequest request,  HttpServletResponse response, Map<String, String> parameters) throws ServletException, IOException {
        int eventId = Integer.parseInt(parameters.get("eventId"));
        Optional<Event> eventOptional = eventService.getEvent(eventId);

        if (eventOptional.isPresent()) {
            request.setAttribute("event", eventOptional.get());
            render("event.jsp", request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void addParticipant(HttpServletRequest request,  HttpServletResponse response, Map<String, String> parameters) throws ServletException, IOException {

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
