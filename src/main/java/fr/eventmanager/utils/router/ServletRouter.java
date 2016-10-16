package fr.eventmanager.utils.router;

import fr.eventmanager.controller.Servlet;
import fr.eventmanager.utils.HttpMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ServletRouter (Singleton)
 *
 * Store all available routes with the associated http method and the related servlet and method to call.
 *
 * @author Clément Garbay
 */
public class ServletRouter {

    private static ServletRouter INSTANCE = null;

    private Map<HttpMethod, List<Route>> routes;

    private ServletRouter() {
        this.routes = new HashMap<>();
    }

    public static synchronized ServletRouter getInstance() {
        if (INSTANCE == null) INSTANCE = new ServletRouter();
        return INSTANCE;
    }

    /**
     * Bind a new route to a servlet and method name (use temporary RoutePath object for a more confortable syntax)
     *
     * @param routeId       The route id to use in jsp files
     * @param httpMethod    The associated http method for this route
     * @param pathBase      The base of the route path
     * @param pathExtension The variable extension of the route path (a pattern to match)
     * @param isProtected   Required authentication to access to the resource (true by default)
     * @return
     */
    public RoutePath bind(RouteId routeId, HttpMethod httpMethod, String pathBase, Pattern pathExtension, boolean isProtected) {
        return new RoutePath(this, httpMethod, routeId, pathBase, pathExtension, isProtected);
    }

    public RoutePath bind(RouteId routeId, HttpMethod httpMethod, String pathBase, Pattern pathExtension) {
        return bind(routeId, httpMethod, pathBase, pathExtension, true);
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
        String path = (pathInfo != null) ? pathInfo : "/";

        Optional<Route> routeOptional = getRoute(httpMethod, path);

        if (!routeOptional.isPresent()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Route route = routeOptional.get();

        // TODO : use userService ?
        boolean userIsAuthenticated = true;

        if (route.isProtected() && !userIsAuthenticated) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        Map<String, String> parameters = extractParametersRouteInPath(route, path);
        introspectMethodInServlet(route.getServletToCall(), route.getMethodNameToCall(), request, response, parameters);
    }

    /**
     * Get the full path from a route identifier.
     *
     * @param routeId   The related route id
     * @return          The corresponding full path
     */
    public String getFullPath(RouteId routeId) {
        return routes.values()
                .stream()
                .map(Collection::stream)
                .flatMap(routesByHttpMethod -> routesByHttpMethod.filter(route -> route.getRouteId().equals(routeId)))
                .map(Route::getFullPath)
                .findFirst() // TODO
                .orElse("");
    }

    ServletRouter registerRoute(HttpMethod httpMethod, Route route) {
        List<Route> routesForHttpMethod = routes.get(httpMethod);
        if (routesForHttpMethod == null) routesForHttpMethod = new ArrayList<>();

        routesForHttpMethod.add(route);
        routes.put(httpMethod, routesForHttpMethod);

        return this;
    }

    private Map<String, String> extractParametersRouteInPath(Route route, String path) {
        Pattern pattern = route.getPathExtension();
        Matcher patternMatcher = pattern.matcher(path);

        patternMatcher.matches(); // MANDATORY (TOREVIEW)

        Map<String, String> parameters = new HashMap<>();
        getNamedGroupCandidates(pattern.pattern())
                .forEach(groupName -> parameters.put(groupName, patternMatcher.group(groupName)));

        return parameters;
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

    private Set<String> getNamedGroupCandidates(String regex) {
        Set<String> namedGroups = new TreeSet<>();
        Matcher matcher = Pattern.compile("\\(\\?<([a-zA-Z][a-zA-Z0-9]*)>").matcher(regex);

        while (matcher.find()) {
            namedGroups.add(matcher.group(1));
        }

        return namedGroups;
    }

    private void introspectMethodInServlet(Servlet servlet, String methodName, HttpServletRequest request, HttpServletResponse response, Map<String, String> parameters) throws IOException {

        Class<?>[] argsClasses = {HttpServletRequest.class, HttpServletResponse.class, Map.class};
        Object[] args = {request, response, parameters};

        if (!proceedToIntrospect(servlet, methodName, argsClasses, args)) {

            Class<?>[] argsClasses1 = {HttpServletRequest.class, HttpServletResponse.class};
            Object[] args1 = {request, response};

            if (!proceedToIntrospect(servlet, methodName, argsClasses1, args1)) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }

    private boolean proceedToIntrospect(Servlet servlet, String methodName, Class<?>[] argsClasses, Object... args) {
        boolean success = true;

        try {
            Method method = servlet.getClass().getDeclaredMethod(methodName, argsClasses);
            method.setAccessible(true);
            method.invoke(servlet, args);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            success = false;
        }

        return success;
    }

}
