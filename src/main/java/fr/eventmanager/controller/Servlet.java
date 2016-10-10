package fr.eventmanager.controller;

import fr.eventmanager.utils.ParsedUrl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Clément Garbay
 */
public abstract class Servlet extends HttpServlet {

    private String actionName;
    private String parameter;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        parseUrl(req.getRequestURL().toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        parseUrl(req.getRequestURL().toString());
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        parseUrl(req.getRequestURL().toString());
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        parseUrl(req.getRequestURL().toString());
    }

    protected void parseUrl(String url) {
        ParsedUrl parsedUrl = new ParsedUrl(url);
        this.actionName = parsedUrl.getActionName();
        this.parameter = (parsedUrl.getParameterName().isEmpty()) ? parsedUrl.getActionName() : parsedUrl.getParameterName();
    }

    protected void render(String jspPage, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("jspPage", jspPage);
        getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
    }

    String getActionName() {
        return actionName;
    }

    String getParameter() {
        return parameter;
    }
}
