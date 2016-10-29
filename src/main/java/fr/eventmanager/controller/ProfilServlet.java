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
    private IEventService eventService;

    @Override
    public void init() throws ServletException {
        super.init();

        eventService = new EventService(new EventDAO());

        bind(HttpMethod.GET, Path.PROFIL).to(this::displayProfilPage);
    }

    @Override
    public void destroy() {
        super.destroy();
        this.eventService.close();
    }

    private void displayProfilPage(WrappedHttpServlet wrappedHttpServlet) throws IOException {
        HttpServletRequest request = wrappedHttpServlet.getRequest();
        HttpServletResponse response = wrappedHttpServlet.getResponse();

        User currentUser = SecurityService.getLoggedUser(request);

        request.setAttribute("eventsSuscribed", eventService.findByParticipant(currentUser));
        request.setAttribute("eventsCreated", eventService.findByOwner(currentUser));

        render(request, response, "profil.jsp");
    }

}