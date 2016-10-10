package fr.eventmanager.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Clément Garbay
 */
public class EventsServlet extends Servlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req,resp);

        switch (getActionName()) {
            case "default":
                getEvents(req, resp);
                break;
            default:
                try {
                    int eventId = Integer.parseInt(getParameter());
                    getEvent(req, resp, eventId);
                } catch (NumberFormatException nfe) {
                    resp.sendError(404);
                }

        }
    }

    private void getEvents(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO : add data

        render("events.jspf", req, resp);
    }

    private void getEvent(HttpServletRequest req, HttpServletResponse resp, int eventId) throws ServletException, IOException {
        req.setAttribute("eventId", eventId);
        render("event.jspf", req, resp);
    }
}
