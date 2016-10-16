package fr.eventmanager.controller;

import fr.eventmanager.security.SecurityService;
import fr.eventmanager.utils.Alert;
import fr.eventmanager.utils.HttpMethod;
import fr.eventmanager.utils.router.Route;
import fr.eventmanager.utils.router.ServletRouter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import fr.eventmanager.utils.URLUtils;

/**
 * @author Clément Garbay
 */
public abstract class Servlet extends HttpServlet {
    ServletRouter servletRouter;
    SecurityService securityService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        securityService = (SecurityService) config.getServletContext().getAttribute(SecurityService.SECURITY_SERVICE);
        if (securityService == null) {
            securityService = new SecurityService();
            config.getServletContext().setAttribute(SecurityService.SECURITY_SERVICE, securityService);
        }

        new URLUtils();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        servletRouter.process(HttpMethod.GET, request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        servletRouter.process(HttpMethod.POST, request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        servletRouter.process(HttpMethod.PUT, request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        servletRouter.process(HttpMethod.DELETE, request, response);
    }

    void render(String partialPage, HttpServletRequest request, HttpServletResponse response, Alert alert) throws ServletException, IOException {
        request.setAttribute("partialPage", partialPage);

        if (alert != null) {
            request.setAttribute("alertType", alert.getType().toString());
            request.setAttribute("alertMessage", alert.getMessage());
        }

        getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
    }

    void render(String partialPage, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        render(partialPage, request, response, null);
    }
}
