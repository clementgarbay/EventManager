package fr.eventmanager.core.router;

import fr.eventmanager.core.security.SecurityService;
import fr.eventmanager.core.session.SessionManager;
import fr.eventmanager.core.utils.Alert;
import fr.eventmanager.core.utils.PreparedMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static fr.eventmanager.core.utils.Utils.ifNull;
import static java.util.Objects.isNull;

/**
 * ServletRouter
 *
 * Store all available routes with the associated http method and the consumer to apply.
 *
 * @author Clément Garbay
 */
public abstract class ServletRouter extends HttpServlet {

    private Map<HttpMethod, List<Route>> routes;

    public ServletRouter() {
        this.routes = new HashMap<>();
    }

    /**
     * Bind a path to a servlet's function (use a temporary "RouteWithoutConsumer" object for a more confortable syntax)
     *
     * @param path          The path item
     * @param isProtected   Required authentication to access to the resource (true by default)
     * @return
     */
    protected RouteWithoutConsumer bind(HttpMethod httpMethod, Path path, boolean isProtected) {
        return new RouteWithoutConsumer(this, httpMethod, path, isProtected);
    }

    protected RouteWithoutConsumer bind(HttpMethod httpMethod, Path path) {
        return bind(httpMethod, path, true);
    }

    /**
     * Process to introspect servlet's method from request and http method.
     *
     * @param httpMethod    The related http method
     * @param request       The HttpServletRequest corresponding to the http call
     * @param response      The HttpServletResponse to return to the http call
     * @throws IOException
     */
    protected void process(HttpMethod httpMethod, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathStr = ifNull(request.getPathInfo(), request.getRequestURI());
        Optional<Route> routeOptional = getRoute(httpMethod, pathStr);

        if (!routeOptional.isPresent()) {
            redirect(request, response, Path.HOME.getFullPath(), Alert.danger(PreparedMessage.NOT_FOUND.getMessage()));
            return;
        }

        Route route = routeOptional.get();

        boolean userIsAuthenticated = SecurityService.isLogged(request);

        if (route.isProtected() && !userIsAuthenticated) {
            redirect(request, response, Path.HOME.getFullPath(), Alert.danger(PreparedMessage.UNAUTHORIZED.getMessage()));
            return;
        }

        Map<String, String> parameters = route.getPath().extractParametersOf(pathStr);
        WrappedHttpServlet wrappedHttpServlet = new WrappedHttpServlet(request, response, parameters);

        try {
            wrappedHttpServlet.getRequest().setCharacterEncoding("UTF-8");
            route.getConsumer().accept(wrappedHttpServlet);
        } catch (ServletException e) {
            e.printStackTrace();
            redirect(request, response, Path.HOME.getFullPath(), Alert.danger(PreparedMessage.INTERNAL_SERVER_ERROR.getMessage()));
        }
    }

    ServletRouter registerRoute(HttpMethod httpMethod, Route route) {
        List<Route> routesForHttpMethod = routes.get(httpMethod);
        if (isNull(routesForHttpMethod)) routesForHttpMethod = new ArrayList<>();

        routesForHttpMethod.add(route);
        routes.put(httpMethod, routesForHttpMethod);

        return this;
    }

    protected void redirect(HttpServletRequest request, HttpServletResponse response, String endPoint, Alert alert) throws IOException {
        if (!isNull(alert)) SessionManager.set(request, "ALERT", alert);
        response.sendRedirect(getServletContext().getContextPath() + endPoint);
    }

    protected void redirect(HttpServletRequest request, HttpServletResponse response, String endPoint) throws IOException {
        redirect(request, response, endPoint, null);
    }

    private Optional<Route> getRoute(HttpMethod httpMethod, String path) {
        List<Route> routesForHttpMethod = routes.get(httpMethod);

        if (!isNull(routesForHttpMethod)) {
            return routesForHttpMethod
                    .stream()
                    .filter(route -> route.matchRoute(path))
                    .findFirst();
        }

        return Optional.empty();
    }

}
