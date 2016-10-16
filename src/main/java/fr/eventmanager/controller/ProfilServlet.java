package fr.eventmanager.controller;

import fr.eventmanager.utils.HttpMethod;
import fr.eventmanager.utils.router.RouteId;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

public class ProfilServlet extends Servlet {

    static final String ROUTE_BASE = "/profil";

    @Override
    public void init() throws ServletException {
        super.init();

        super.servletRouter
                .bind(RouteId.PROFIL, HttpMethod.GET, ROUTE_BASE, Pattern.compile("/"), false)
                    .to(this, "displayProfilPage");
    }

    private void displayProfilPage(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException {
        render("profil.jsp", request, response);
    }

}