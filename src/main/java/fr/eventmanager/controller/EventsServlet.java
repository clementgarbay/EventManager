package fr.eventmanager.controller;

import fr.eventmanager.core.router.HttpMethod;
import fr.eventmanager.core.router.Path;
import fr.eventmanager.core.router.WrappedHttpServlet;
import fr.eventmanager.core.utils.Alert;
import fr.eventmanager.core.utils.Alert.AlertType;
import fr.eventmanager.core.utils.PreparedMessage;
import fr.eventmanager.dao.impl.EventDAO;
import fr.eventmanager.entity.Event;
import fr.eventmanager.entity.User;
import fr.eventmanager.entity.helper.EventHelper;
import fr.eventmanager.core.security.SecurityService;
import fr.eventmanager.service.IEventService;
import fr.eventmanager.service.impl.EventService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import static java.util.Objects.isNull;

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
        bind(HttpMethod.GET, Path.EVENTS_NEW).to(this::displayNewEventPage);
        bind(HttpMethod.GET, Path.EVENT_EDIT).to(this::displayEditEventPage);
        bind(HttpMethod.POST, Path.EVENTS_NEW).to(this::addEvent);
        bind(HttpMethod.POST, Path.EVENT_EDIT).to(this::editEvent);
        bind(HttpMethod.POST, Path.EVENT_SUBSCRIBE).to(this::subscribe);
        bind(HttpMethod.POST, Path.EVENT_UNSUBSCRIBE).to(this::unsubscribe);
    }

    @Override
    public void destroy() {
        super.destroy();
        this.eventService.close();
    }

    private void displayEventsPage(WrappedHttpServlet wrappedHttpServlet) throws IOException {
        HttpServletRequest request = wrappedHttpServlet.getRequest();
        HttpServletResponse response = wrappedHttpServlet.getResponse();

        request.setAttribute("events", eventService.getEvents());
        render(request, response, "events.jsp");
    }

    private void displayEventPage(WrappedHttpServlet wrappedHttpServlet) throws IOException {
        HttpServletRequest request = wrappedHttpServlet.getRequest();
        HttpServletResponse response = wrappedHttpServlet.getResponse();
        Map<String, String> parameters = wrappedHttpServlet.getParameters();

        int eventId = Integer.parseInt(parameters.get("eventId"));
        Optional<Event> eventOptional = eventService.getEvent(eventId);

        if (!eventOptional.isPresent()) {
            redirect(request, response, Path.EVENTS.getFullPath(), Alert.danger(PreparedMessage.NOT_FOUND.getMessage()));
            return;
        }

        request.setAttribute("event", eventOptional.get());
        render(request, response, "event.jsp");
    }

    private void displayNewEventPage(WrappedHttpServlet wrappedHttpServlet) throws IOException {
        HttpServletRequest request = wrappedHttpServlet.getRequest();
        HttpServletResponse response = wrappedHttpServlet.getResponse();

        render(request, response, "events_new.jsp");
    }

    private void displayEditEventPage(WrappedHttpServlet wrappedHttpServlet) throws IOException {
        HttpServletRequest request = wrappedHttpServlet.getRequest();
        HttpServletResponse response = wrappedHttpServlet.getResponse();
        Map<String, String> parameters = wrappedHttpServlet.getParameters();

        int eventId = Integer.parseInt(parameters.get("eventId"));
        Optional<Event> eventOptional = eventService.getEvent(eventId);

        if (!eventOptional.isPresent()) {
            redirect(request, response, Path.EVENTS.getFullPath(), Alert.danger(PreparedMessage.NOT_FOUND.getMessage()));
            return;
        }

        Event event = eventOptional.get();
        User loggedUser = SecurityService.getLoggedUser(request);

        // Check if the logged user is the owner of this event
        if (isNull(loggedUser) || !loggedUser.getId().equals(event.getOwner().getId())) {
            String path = Path.EVENT.getFullPath(Collections.singletonMap("eventId", Integer.toString(event.getId())));
            redirect(request, response, path, new Alert(AlertType.DANGER, PreparedMessage.FORBIDDEN.getMessage()));
            return;
        }

        request.setAttribute("event", event);
        render(request, response, "event_edit.jsp");
    }

    private void addEvent(WrappedHttpServlet wrappedHttpServlet) throws IOException {
        HttpServletRequest request = wrappedHttpServlet.getRequest();
        HttpServletResponse response = wrappedHttpServlet.getResponse();

        Event eventBuilt = EventHelper.build(request);

        eventBuilt
            .validate()
            .apply(success -> {
                if (eventService.addEvent(eventBuilt)) {
                    String path = Path.EVENT.getFullPath(Collections.singletonMap("eventId", Integer.toString(eventBuilt.getId())));
                    redirect(request, response, path);
                } else {
                    request.setAttribute("event", eventBuilt);
                    render(request, response, "events_new.jsp", Alert.danger(PreparedMessage.INTERNAL_SERVER_ERROR.getMessage()));
                }
            }, error -> {
                request.setAttribute("event", eventBuilt);
                render(request, response, "events_new.jsp", Alert.danger(error));
            });
    }

    private void editEvent(WrappedHttpServlet wrappedHttpServlet) throws IOException {
        HttpServletRequest request = wrappedHttpServlet.getRequest();
        HttpServletResponse response = wrappedHttpServlet.getResponse();
        Map<String, String> parameters = wrappedHttpServlet.getParameters();

        int eventId = Integer.parseInt(parameters.get("eventId"));

        Optional<Event> eventOptional = eventService.getEvent(eventId);
        Event modifiedEventBuilt = EventHelper.build(request);

        if (!eventOptional.isPresent()) {
            redirect(request, response, Path.EVENTS.getFullPath(), Alert.danger(PreparedMessage.NOT_FOUND.getMessage()));
            return;
        }

        Event event = eventOptional.get();
        User loggedUser = SecurityService.getLoggedUser(request);

        String redirectionPath = Path.EVENT.getFullPath(Collections.singletonMap("eventId", Integer.toString(event.getId())));

        // Check if the logged user is the owner of this event
        if (isNull(loggedUser) || loggedUser.getId().equals(event.getOwner().getId())) {
            redirect(request, response, redirectionPath, Alert.danger(PreparedMessage.FORBIDDEN.getMessage()));
            return;
        }

        modifiedEventBuilt.setId(eventId);

        modifiedEventBuilt
            .validate()
            .apply(success -> {
                if (!eventService.updateEvent(modifiedEventBuilt)) {
                    redirect(request, response, redirectionPath);
                } else {
                    request.setAttribute("event", modifiedEventBuilt);
                    render(request, response, "event_edit.jsp", Alert.danger(PreparedMessage.INTERNAL_SERVER_ERROR.getMessage()));
                }
            }, error -> {
                request.setAttribute("event", modifiedEventBuilt);
                render(request, response, "event_edit.jsp", Alert.danger(error));
            });
    }

    private void subscribe(WrappedHttpServlet wrappedHttpServlet) throws IOException {
        HttpServletRequest request = wrappedHttpServlet.getRequest();
        HttpServletResponse response = wrappedHttpServlet.getResponse();
        Map<String, String> parameters = wrappedHttpServlet.getParameters();

        int eventId = Integer.parseInt(parameters.get("eventId"));
        Optional<Event> eventOptional = eventService.getEvent(eventId);

        if (!eventOptional.isPresent()) {
            redirect(request, response, Path.EVENTS.getFullPath(), Alert.danger(PreparedMessage.NOT_FOUND.getMessage()));
            return;
        }

        Event event = eventOptional.get();
        User loggedUser = SecurityService.getLoggedUser(request);

        String redirectionPath = Path.EVENT.getFullPath(Collections.singletonMap("eventId", Integer.toString(event.getId())));

        if (!eventService.subscribe(event, loggedUser)) {
            redirect(request, response, redirectionPath, Alert.danger(PreparedMessage.INTERNAL_SERVER_ERROR.getMessage()));
            return;
        }

        redirect(request, response, redirectionPath);
    }

    private void unsubscribe(WrappedHttpServlet wrappedHttpServlet) throws IOException {
        HttpServletRequest request = wrappedHttpServlet.getRequest();
        HttpServletResponse response = wrappedHttpServlet.getResponse();
        Map<String, String> parameters = wrappedHttpServlet.getParameters();

        int eventId = Integer.parseInt(parameters.get("eventId"));
        Optional<Event> eventOptional = eventService.getEvent(eventId);

        if (!eventOptional.isPresent()) {
            redirect(request, response, Path.EVENTS.getFullPath(), Alert.danger(PreparedMessage.NOT_FOUND.getMessage()));
            return;
        }

        Event event = eventOptional.get();
        User loggedUser = SecurityService.getLoggedUser(request);

        String redirectionPath = Path.EVENT.getFullPath(Collections.singletonMap("eventId", Integer.toString(event.getId())));

        if (!event.isParticipant(loggedUser)) {
            redirect(request, response, redirectionPath, Alert.danger("Vous ne pouvez pas vous désinscrire d'un événement auquel vous ne participez pas."));
            return;
        }

        if (!eventService.unsubscribe(event, loggedUser)) {
            redirect(request, response, redirectionPath, Alert.danger(PreparedMessage.INTERNAL_SERVER_ERROR.getMessage()));
            return;
        }

        redirect(request, response, redirectionPath);
    }

}
