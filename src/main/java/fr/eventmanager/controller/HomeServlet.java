package fr.eventmanager.controller;

import fr.eventmanager.utils.HttpMethod;
import fr.eventmanager.utils.router.Path;

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

        bind(HttpMethod.GET, Path.HOME, false).to("displayHomePage");
    }

    private void displayHomePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ok!");

        render("events.jsp", request, response);
    }
}
