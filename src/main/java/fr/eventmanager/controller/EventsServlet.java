package fr.eventmanager.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Clément Garbay
 */
public class EventsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = (String) req.getAttribute("action");

        switch (action) {
            case "default":
                get(req,resp);
                break;
            default:
                try {
                    int eventId = Integer.parseInt(action);
                    getEvent(eventId, req,resp);
                } catch (NumberFormatException nfe) {
                    resp.sendError(400);
                }

        }
    }

    private void get(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println("<h1>Get : Event : index</h1>");
    }

    private void getEvent(int eventId, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println("<h1>Get : Event : " + eventId + "</h1>");
    }
}
