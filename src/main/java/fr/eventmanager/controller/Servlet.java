package fr.eventmanager.controller;

import fr.eventmanager.security.SecurityService;
import fr.eventmanager.utils.Alert;
import fr.eventmanager.utils.router.HttpMethod;
import fr.eventmanager.utils.router.ServletRouter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    void render(HttpServletRequest request, HttpServletResponse response, String partialPage, Alert alert) {
        request.setAttribute("partialPage", partialPage);

        request.setAttribute("SECURITY_IS_LOGGED", SecurityService.isLogged(request));
        request.setAttribute("SECURITY_LOGGED_USER", SecurityService.getLoggedUser(request));

        if (alert != null) {
            request.setAttribute("alertType", alert.getType().toString());
            request.setAttribute("alertMessage", alert.getMessage());
        }

        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/WEB-INF/index.jsp");

        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
            try {
                response.sendError(SC_INTERNAL_SERVER_ERROR);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    void render(HttpServletRequest request, HttpServletResponse response, String partialPage) {
        render(request, response, partialPage, null);
    }

    void redirect(HttpServletResponse response, String endPoint) throws IOException {
        response.sendRedirect(getServletContext().getContextPath() + endPoint);
    }
}
