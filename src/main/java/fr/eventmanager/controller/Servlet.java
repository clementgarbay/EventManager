package fr.eventmanager.controller;

import fr.eventmanager.utils.router.HttpMethod;
import fr.eventmanager.utils.router.ServletRouter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Clément Garbay
 */
public abstract class Servlet extends HttpServlet {

    ServletRouter servletRouter;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        servletRouter.process(HttpMethod.GET, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        servletRouter.process(HttpMethod.POST, req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        servletRouter.process(HttpMethod.PUT, req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        servletRouter.process(HttpMethod.DELETE, req, resp);
    }

    void render(String jspPage, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("jspPage", jspPage);
        getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
    }
}
