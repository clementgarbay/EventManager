package fr.eventmanager.controller;

import fr.eventmanager.utils.HttpMethod;
import fr.eventmanager.utils.router.Route;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

@WebServlet(name = "ProfilServlet", urlPatterns = {ProfilServlet.ROUTE_BASE + "/*"})
public class ProfilServlet extends Servlet {

    static final String ROUTE_BASE = "/profil";

    @Override
    public void init() throws ServletException {
        super.init();

        registerRoute(HttpMethod.GET, new Route(ROUTE_BASE, Pattern.compile("/"), "displayProfilPage"));
    }

    private void displayProfilPage(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException {
        render("profil.jsp", request, response);
    }

}