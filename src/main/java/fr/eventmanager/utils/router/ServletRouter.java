package fr.eventmanager.utils.router;

import fr.eventmanager.utils.HttpMethod;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ServletRouter
 *
 * Store all available routes with the associated http method and the related method to call.
 *
 * @author Clément Garbay
 */
public abstract class ServletRouter extends HttpServlet {

    private Map<HttpMethod, List<Route>> routes;

    public ServletRouter() {
        this.routes = new HashMap<>();
    }

    /**
     * Register new route
     *
     * @param httpMethod    The associated http method for this route
     * @param route         The route to add
     * @return this
     */
    public ServletRouter registerRoute(HttpMethod httpMethod, Route route) {
        List<Route> routesForHttpMethod = routes.get(httpMethod);
        if (routesForHttpMethod == null) routesForHttpMethod = new ArrayList<>();

        routesForHttpMethod.add(route);
        routes.put(httpMethod, routesForHttpMethod);

        return this;
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
        introspectMethod(route.getMethodNameToCall(), request, response, parameters);
    }

    private Map<String, String> extractParametersRouteInPath(Route route, String path) {
        Pattern pattern = route.getPattern();
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
                    .filter(route -> route.matchPattern(path))
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

    private void introspectMethod(String methodName, HttpServletRequest request, HttpServletResponse response, Map<String, String> parameters) throws IOException {

        Class<?>[] argsClasses = {HttpServletRequest.class, HttpServletResponse.class, Map.class};
        Object[] args = {request, response, parameters};

        if (!proceedToIntrospect(methodName, argsClasses, args)) {

            Class<?>[] argsClasses1 = {HttpServletRequest.class, HttpServletResponse.class};
            Object[] args1 = {request, response};

            if (!proceedToIntrospect(methodName, argsClasses1, args1)) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }

    private boolean proceedToIntrospect(String methodName, Class<?>[] argsClasses, Object... args) {
        boolean success = true;

        try {
            Method method = this.getClass().getDeclaredMethod(methodName, argsClasses);
            method.setAccessible(true);
            method.invoke(this, args);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            success = false;
        }

        return success;
    }

}
