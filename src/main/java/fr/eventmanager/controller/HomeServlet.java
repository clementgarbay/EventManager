package fr.eventmanager.controller;

import fr.eventmanager.utils.router.HttpMethod;
import fr.eventmanager.utils.router.Path;
import fr.eventmanager.utils.router.WrappedHttpServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Clément Garbay
 */
@WebServlet(name = "HomeServlet", urlPatterns = {"/"})
public class HomeServlet extends Servlet {

    @Override
    public void init() throws ServletException {
        super.init();

        bind(HttpMethod.GET, Path.HOME, false).to(this::displayHomePage);
    }

    private void displayHomePage(WrappedHttpServlet wrappedHttpServlet) {
        HttpServletRequest request = wrappedHttpServlet.getRequest();
        HttpServletResponse response = wrappedHttpServlet.getResponse();

        render("events.jsp", request, response);
    }
}
