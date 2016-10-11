package fr.eventmanager.controller;

import fr.eventmanager.utils.HttpMethod;
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

    @Override
    public void init() throws ServletException {
        super.init();

        super.servletRouter = new ServletRouter(this)
                .registerRoute(HttpMethod.GET, Pattern.compile("/"), "getEvents")
                .registerRoute(HttpMethod.GET, Pattern.compile("/(?<eventId>\\d+)"), "getEvent")
                .registerRoute(HttpMethod.POST, Pattern.compile("/(?<eventId>\\d+)"), "addEvent")
                .registerRoute(HttpMethod.GET, Pattern.compile("/subscribe/(?<eventId>\\d+)"), "subscribe");
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

        System.out.println(name);
        System.out.println(email);
        System.out.println(confirmEmail);

        render("event.jspf", request, response);
    }


}
