package fr.eventmanager.controller;

import fr.eventmanager.utils.HttpMethod;
import fr.eventmanager.utils.router.RouteId;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * @author Clément Garbay
 */
public class HomeServlet extends Servlet {

    static final String ROUTE_BASE = "";

    @Override
    public void init() throws ServletException {
        super.init();

        super.servletRouter
                .bind(RouteId.HOME, HttpMethod.GET, ROUTE_BASE, Pattern.compile("/"), false)
                    .to(this, "displayHomePage");
    }

    private void displayHomePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ok!");

        render("events.jsp", request, response);
    }
}
