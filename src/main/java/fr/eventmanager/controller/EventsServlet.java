package fr.eventmanager.controller;

import fr.eventmanager.dao.impl.EventDAO;
import fr.eventmanager.entity.Event;
import fr.eventmanager.entity.helper.EventHelper;
import fr.eventmanager.service.IEventService;
import fr.eventmanager.service.impl.EventService;
import fr.eventmanager.utils.Alert;
import fr.eventmanager.utils.router.HttpMethod;
import fr.eventmanager.utils.router.Path;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Clément Garbay
 * @author Paul Defois
 */
@WebServlet(name = "EventServlet", urlPatterns = {Path.PathConstants.EVENTS + "/*"})
public class EventsServlet extends Servlet {

    private IEventService eventService;

    @Override
    public void init() throws ServletException {
        super.init();

        // TODO : use injection dependency
        this.eventService = new EventService(new EventDAO());

        bind(HttpMethod.GET, Path.EVENTS, false).to("displayEventsPage");
        bind(HttpMethod.GET, Path.EVENT, false).to("displayEventPage");
        bind(HttpMethod.GET, Path.NEW_EVENT, false).to("displayNewEventPage");
        bind(HttpMethod.POST, Path.NEW_EVENT).to("addEvent");
        bind(HttpMethod.POST, Path.EVENT, false).to("addParticipant");
    }

    private void displayEventsPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("events", eventService.getEvents());
        render("events.jsp", request, response);
    }

    private void displayEventPage(HttpServletRequest request,  HttpServletResponse response, Map<String, String> parameters) throws ServletException, IOException {
        int eventId = Integer.parseInt(parameters.get("eventId"));
        Optional<Event> eventOptional = eventService.getEvent(eventId);

        if (eventOptional.isPresent()) {
            request.setAttribute("event", eventOptional.get());
            render("event.jsp", request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void displayNewEventPage(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException {
        render("event_new.jsp", request, response);
    }

    private void addEvent(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException {
        Event eventBuilt = EventHelper.build(request);

        eventBuilt
            .validate()
            .apply(success -> {
                Event event = eventService.addEvent(success.getEntity());

                // TODO : control if there is no error (currently 500 if failed)

                request.setAttribute("event", event);
                render("event.jsp", request, response);
            }, error -> {
                request.setAttribute("event", error.getEntity());
                render("event_new.jsp", request, response, new Alert(Alert.AlertType.DANGER, error.getMessage()));
            });
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
