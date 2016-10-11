package fr.eventmanager.controller;

import fr.eventmanager.utils.router.HttpMethod;
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
                .registerRouter(HttpMethod.GET, Pattern.compile("/"), "getEvents")
                .registerRouter(HttpMethod.GET, Pattern.compile("/(?<eventId>\\d+)"), "getEvent");
    }

    private void getEvents(HttpServletRequest request, HttpServletResponse response, String path, Map<String, String> parameters) throws ServletException, IOException {
        // TODO : add data
        render("events.jspf", request, response);
    }

    private void getEvent(HttpServletRequest request,  HttpServletResponse response, String path, Map<String, String> parameters) throws ServletException, IOException {

        System.out.println(parameters);

//        req.setAttribute("eventId", eventId);
        render("event.jspf", request, response);
    }
}
