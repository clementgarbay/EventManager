package fr.eventmanager.controller;

import fr.eventmanager.utils.HttpMethod;
import fr.eventmanager.utils.router.Route;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * @author Clément Garbay
 */
@WebServlet(name = "HomeServlet", urlPatterns = {HomeServlet.ROUTE_BASE + "/"})
public class HomeServlet extends Servlet {

    static final String ROUTE_BASE = "";

    @Override
    public void init() throws ServletException {
        super.init();

        registerRoute(HttpMethod.GET, new Route(ROUTE_BASE, Pattern.compile("/"), "displayHomePage"));
    }

    private void displayHomePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ok!");

        render("events.jsp", request, response);
    }
}
