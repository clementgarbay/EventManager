package fr.eventmanager.controller;

import fr.eventmanager.utils.HttpMethod;
import fr.eventmanager.utils.router.Route;
import fr.eventmanager.utils.router.ServletRouter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

public class ProfilServlet extends Servlet {

    @Override
    public void init() throws ServletException {
        super.init();

        super.servletRouter = new ServletRouter(this)
                .registerRoute(HttpMethod.GET, new Route(Pattern.compile("/"), "displayProfilPage"));
    }

    private void displayProfilPage(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException {
        render("profil.jsp", request, response);
    }

}