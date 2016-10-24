package fr.eventmanager.controller;

import fr.eventmanager.dao.impl.EventDAO;
import fr.eventmanager.entity.Event;
import fr.eventmanager.entity.helper.EventHelper;
import fr.eventmanager.service.IEventService;
import fr.eventmanager.service.impl.EventService;
import fr.eventmanager.utils.Alert;
import fr.eventmanager.utils.Alert.AlertType;
import fr.eventmanager.utils.router.HttpMethod;
import fr.eventmanager.utils.router.Path;
import fr.eventmanager.utils.router.WrappedHttpServlet;

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

        this.eventService = new EventService(new EventDAO());

        bind(HttpMethod.GET, Path.EVENTS, false).to(this::displayEventsPage);
        bind(HttpMethod.GET, Path.EVENT, false).to(this::displayEventPage);
        bind(HttpMethod.GET, Path.NEW_EVENT).to(this::displayNewEventPage);
        bind(HttpMethod.GET, Path.EDIT_EVENT).to(this::displayEditEventPage);
        bind(HttpMethod.POST, Path.NEW_EVENT).to(this::addEvent);
        bind(HttpMethod.POST, Path.EDIT_EVENT).to(this::editEvent);
        bind(HttpMethod.POST, Path.EVENT, false).to(this::addParticipant);
    }

    private void displayEventsPage(WrappedHttpServlet wrappedHttpServlet) {
        HttpServletRequest request = wrappedHttpServlet.getRequest();
        HttpServletResponse response = wrappedHttpServlet.getResponse();

        request.setAttribute("events", eventService.getEvents());
        render("events.jsp", request, response);
    }

    private void displayEventPage(WrappedHttpServlet wrappedHttpServlet) throws IOException {
        HttpServletRequest request = wrappedHttpServlet.getRequest();
        HttpServletResponse response = wrappedHttpServlet.getResponse();
        Map<String, String> parameters = wrappedHttpServlet.getParameters();

        int eventId = Integer.parseInt(parameters.get("eventId"));
        Optional<Event> eventOptional = eventService.getEvent(eventId);

        if (eventOptional.isPresent()) {
            request.setAttribute("event", eventOptional.get());
            render("event.jsp", request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void displayNewEventPage(WrappedHttpServlet wrappedHttpServlet) {
        HttpServletRequest request = wrappedHttpServlet.getRequest();
        HttpServletResponse response = wrappedHttpServlet.getResponse();

        render("event_new.jsp", request, response);
    }

    private void displayEditEventPage(WrappedHttpServlet wrappedHttpServlet) throws IOException {
        HttpServletRequest request = wrappedHttpServlet.getRequest();
        HttpServletResponse response = wrappedHttpServlet.getResponse();
        Map<String, String> parameters = wrappedHttpServlet.getParameters();

        int eventId = Integer.parseInt(parameters.get("eventId"));
        Optional<Event> eventOptional = eventService.getEvent(eventId);

        if (eventOptional.isPresent()) {
            request.setAttribute("event", eventOptional.get());
            render("event_edit.jsp", request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void addEvent(WrappedHttpServlet wrappedHttpServlet) {
        HttpServletRequest request = wrappedHttpServlet.getRequest();
        HttpServletResponse response = wrappedHttpServlet.getResponse();

        Event eventBuilt = EventHelper.build(request);

        eventBuilt
            .validate()
            .apply(success -> {
                request.setAttribute("event", eventBuilt);

                if (eventService.addEvent(eventBuilt)) {
                    render("event.jsp", request, response);
                } else {
                    render("event_new.jsp", request, response, new Alert(AlertType.DANGER, "Une erreur est survenue. Merci de réessayer."));
                }
            }, error -> {
                request.setAttribute("event", eventBuilt);
                render("event_new.jsp", request, response, new Alert(AlertType.DANGER, error.getMessage()));
            });
    }

    private void editEvent(WrappedHttpServlet wrappedHttpServlet) throws IOException {
        HttpServletRequest request = wrappedHttpServlet.getRequest();
        HttpServletResponse response = wrappedHttpServlet.getResponse();
        Map<String, String> parameters = wrappedHttpServlet.getParameters();

        int eventId = Integer.parseInt(parameters.get("eventId"));

        Optional<Event> eventOptional = eventService.getEvent(eventId);
        Event modifiedEventBuilt = EventHelper.build(request);

        if (eventOptional.isPresent()) {
            modifiedEventBuilt.setId(eventId);

            modifiedEventBuilt
                .validate()
                .apply(success -> {
                    request.setAttribute("event", modifiedEventBuilt);

                    if (eventService.updateEvent(modifiedEventBuilt)) {
                        render("event.jsp", request, response);
                    } else {
                        render("event_edit.jsp", request, response, new Alert(AlertType.DANGER, "Une erreur est survenue. Merci de réessayer."));
                    }
                }, error -> {
                    request.setAttribute("event", modifiedEventBuilt);
                    render("event_edit.jsp", request, response, new Alert(AlertType.DANGER, error.getMessage()));
                });
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void addParticipant(WrappedHttpServlet wrappedHttpServlet) {
        HttpServletRequest request = wrappedHttpServlet.getRequest();
        HttpServletResponse response = wrappedHttpServlet.getResponse();
        Map<String, String> parameters = wrappedHttpServlet.getParameters();

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String confirmEmail = request.getParameter("confirm_email");

        int eventId = Integer.parseInt(parameters.get("eventId"));

        // TODO : check if a user is connected or if the user already exists

        if (!name.isEmpty() && !email.isEmpty() && !confirmEmail.isEmpty()) {
            if (Objects.equals(email, confirmEmail)) {

                // eventService.addParticipant()

                render("event.jsp", request, response, new Alert(AlertType.SUCCESS, "Inscription validée. Vous allez recevoir un email de confirmation."));
            } else {
                render("event.jsp", request, response, new Alert(AlertType.DANGER, "Les emails doivent être identiques."));
            }
        } else {
            render("event.jsp", request, response, new Alert(AlertType.DANGER, "Tous les champs doivent être remplis."));
        }
    }


}
