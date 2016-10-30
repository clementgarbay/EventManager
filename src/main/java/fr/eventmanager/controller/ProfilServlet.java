package fr.eventmanager.controller;

import fr.eventmanager.core.router.HttpMethod;
import fr.eventmanager.core.router.Path;
import fr.eventmanager.core.router.WrappedHttpServlet;
import fr.eventmanager.core.security.SecurityService;
import fr.eventmanager.dao.impl.EventDAO;
import fr.eventmanager.entity.User;
import fr.eventmanager.service.IEventService;
import fr.eventmanager.service.impl.EventService;

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

        bind(HttpMethod.GET, Path.PROFIL).to(this::displayProfilPage);
    }

    private void displayProfilPage(WrappedHttpServlet wrappedHttpServlet) throws IOException {
        HttpServletRequest request = wrappedHttpServlet.getRequest();
        HttpServletResponse response = wrappedHttpServlet.getResponse();

        render(request, response, "profil.jsp");
    }

}