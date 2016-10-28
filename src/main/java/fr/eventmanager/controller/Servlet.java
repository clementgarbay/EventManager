package fr.eventmanager.controller;

import fr.eventmanager.core.router.HttpMethod;
import fr.eventmanager.core.router.ServletRouter;
import fr.eventmanager.core.session.SessionManager;
import fr.eventmanager.core.utils.Alert;
import fr.eventmanager.core.security.SecurityService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Objects.isNull;
import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

/**
 * @author Clément Garbay
 */
public abstract class Servlet extends ServletRouter {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(HttpMethod.GET, request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(HttpMethod.POST, request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(HttpMethod.PUT, request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(HttpMethod.DELETE, request, response);
    }

    protected void render(HttpServletRequest request, HttpServletResponse response, String partialPage, Alert alert) throws IOException {
        request.setAttribute("partialPage", partialPage);

        request.setAttribute("SECURITY_IS_LOGGED", SecurityService.isLogged(request));
        request.setAttribute("SECURITY_LOGGED_USER", SecurityService.getLoggedUser(request));

        if (isNull(alert)) {
            alert = SessionManager.get(request, "ALERT");
            SessionManager.remove(request, "ALERT");
        }

        if (!isNull(alert)) {
            request.setAttribute("alertType", alert.getType().toString());
            request.setAttribute("alertMessage", alert.getMessage());
        }

        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/index.jsp");

        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
            response.sendError(SC_INTERNAL_SERVER_ERROR);
        }
    }

    protected void render(HttpServletRequest request, HttpServletResponse response, String partialPage) throws IOException {
        render(request, response, partialPage, null);
    }

    protected void redirect(HttpServletRequest request, HttpServletResponse response, String endPoint, Alert alert) throws IOException {
        if (!isNull(alert)) SessionManager.set(request, "ALERT", alert);
        response.sendRedirect(getServletContext().getContextPath() + endPoint);
    }

    protected void redirect(HttpServletRequest request, HttpServletResponse response, String endPoint) throws IOException {
        redirect(request, response, endPoint, null);
    }
}
