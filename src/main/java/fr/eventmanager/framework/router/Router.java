package fr.eventmanager.framework.router;

import fr.eventmanager.framework.utils.ParsedUrl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * @author Clément Garbay
 */
public class Router extends HttpServlet {

    private final RouterConfig routerConfig = RouterConfig.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward(req,resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward(req,resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward(req,resp);
    }

    private void forward(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURL().toString();
        ParsedUrl parsedUrl = new ParsedUrl(url);

        Optional<String> servletNameOptional = routerConfig.getServletNameFor(parsedUrl.getControllerName());

        if (servletNameOptional.isPresent()) {
            req.setAttribute("action", parsedUrl.getActionName());
            req.setAttribute("parameter", parsedUrl.getParameterName());
            getServletContext().getNamedDispatcher(servletNameOptional.get()).forward(req,resp);
        } else {
            resp.sendError(404);
        }
    }
}
