package fr.eventmanager.controller;

import fr.eventmanager.dao.impl.EventDAOImpl;
import fr.eventmanager.service.EventService;
import fr.eventmanager.service.impl.EventServiceImpl;
import fr.eventmanager.utils.HttpMethod;
import fr.eventmanager.utils.JspErrorMessage;
import fr.eventmanager.utils.router.ServletRouter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
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
        this.eventService = new EventServiceImpl(new EventDAOImpl());

        super.servletRouter = new ServletRouter(this)
                .registerRoute(HttpMethod.GET, Pattern.compile("/"), "getEvents")
                .registerRoute(HttpMethod.GET, Pattern.compile("/(?<eventId>\\d+)"), "getEvent")
                .registerRoute(HttpMethod.POST, Pattern.compile("/(?<eventId>\\d+)"), "addEvent");
    }

    private void getEvents(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        render("events.jspf", request, response);
    }

    private void getEvent(HttpServletRequest request,  HttpServletResponse response, Map<String, String> parameters) throws ServletException, IOException {
        int eventId = Integer.parseInt(parameters.get("eventId"));

        System.out.println(eventId);

        render("event.jspf", request, response);
    }

    private void addEvent(HttpServletRequest request,  HttpServletResponse response, Map<String, String> parameters) throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String confirmEmail = request.getParameter("confirm_email");

        int eventId = Integer.parseInt(parameters.get("eventId"));

        // TODO : check if a user is connected or if the user already exists

        if (!name.isEmpty() && !email.isEmpty() && !confirmEmail.isEmpty()) {
            if (!email.equals(confirmEmail)) {

                // eventService.addParticipant()

                render("event.jspf", request, response);
            } else {
                request.setAttribute("modalIsActivated", true);
                request.setAttribute("error", new JspErrorMessage("Les emails doivent être identiques."));
            }
        } else {
            request.setAttribute("modalIsActivated", true);
            request.setAttribute("error", new JspErrorMessage("Tous les champs doivent être remplis."));
        }
    }


}
