package fr.eventmanager.controller;

import fr.eventmanager.utils.HttpMethod;
import fr.eventmanager.utils.router.Path;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProfilServlet", urlPatterns = {Path.PathConstants.PROFIL + "/*"})
public class ProfilServlet extends Servlet {

    @Override
    public void init() throws ServletException {
        super.init();

        bind(HttpMethod.GET, Path.PROFIL, false).to("displayProfilPage");
    }

    private void displayProfilPage(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException {
        render("profil.jsp", request, response);
    }

}