package fr.eventmanager.utils.router;

import fr.eventmanager.security.SecurityService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * ServletRouter
 *
 * Store all available routes with the associated http method and method to call.
 *
 * @author Clément Garbay
 */
public abstract class ServletRouter extends HttpServlet {

    private Map<HttpMethod, List<Route>> routes;

    public ServletRouter() {
        this.routes = new HashMap<>();
    }

    /**
     * Bind a path to a servlet's method name (use a temporary "RouteWithoutMethod" object for a more confortable syntax)
     *
     * @param path          The path item
     * @param isProtected   Required authentication to access to the resource (true by default)
     * @return
     */
    public RouteWithoutMethod bind(HttpMethod httpMethod, Path path, boolean isProtected) {
        return new RouteWithoutMethod(this, httpMethod, path, isProtected);
    }

    public RouteWithoutMethod bind(HttpMethod httpMethod, Path path) {
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
    public void process(HttpMethod httpMethod, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathInfo = request.getPathInfo();
        String pathStr = (pathInfo != null) ? pathInfo : "/";

        Optional<Route> routeOptional = getRoute(httpMethod, pathStr);

        if (!routeOptional.isPresent()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Route route = routeOptional.get();

        boolean userIsAuthenticated = SecurityService.isLogged(request);

        if (route.isProtected() && !userIsAuthenticated) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        Map<String, String> parameters = route.getPath().extractParametersOf(pathStr);
        introspectMethodInServlet(route.getMethodNameToCall(), request, response, parameters);
    }

    ServletRouter registerRoute(HttpMethod httpMethod, Route route) {
        List<Route> routesForHttpMethod = routes.get(httpMethod);
        if (routesForHttpMethod == null) routesForHttpMethod = new ArrayList<>();

        routesForHttpMethod.add(route);
        routes.put(httpMethod, routesForHttpMethod);

        return this;
    }

    private Optional<Route> getRoute(HttpMethod httpMethod, String path) {
        List<Route> routesForHttpMethod = routes.get(httpMethod);

        if (routesForHttpMethod != null) {
            return routesForHttpMethod
                    .stream()
                    .filter(route -> route.matchRoute(path))
                    .findFirst();
        }

        return Optional.empty();
    }

    private void introspectMethodInServlet(String methodName, HttpServletRequest request, HttpServletResponse response, Map<String, String> parameters) throws IOException {

        Class<?>[] argsClasses = {HttpServletRequest.class, HttpServletResponse.class, Map.class};
        Object[] args = {request, response, parameters};

        try {
            if (!proceedToIntrospect(methodName, argsClasses, args)) {

                Class<?>[] argsClasses1 = {HttpServletRequest.class, HttpServletResponse.class};
                Object[] args1 = {request, response};

                if (!proceedToIntrospect(methodName, argsClasses1, args1)) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
            }
        } catch (InvocationTargetException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private boolean proceedToIntrospect(String methodName, Class<?>[] argsClasses, Object... args) throws InvocationTargetException {
        boolean success = true;

        try {
            Method method = this.getClass().getDeclaredMethod(methodName, argsClasses);
            method.setAccessible(true);
            method.invoke(this, args);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            success = false;
        }

        return success;
    }

}
