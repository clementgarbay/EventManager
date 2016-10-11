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
 * ServletRouter
 *
 * Store all available routes for a specific servlet with the associated http method and the related method to call.
 *
 * @author Clément Garbay
 */
public class ServletRouter {

    private Servlet servlet;
    private Map<HttpMethod, Map<Pattern, String>> binder;

    public ServletRouter(Servlet servlet) {
        this.servlet = servlet;
        this.binder = new HashMap<>();
    }

    /**
     * Register new route for this servlet.
     *
     * @param httpMethod    The associated http method for this route
     * @param urlPattern    The pattern to match
     * @param methodToCall  The servlet method to call when the url corresponds to the given pattern
     * @return this
     */
    public ServletRouter registerRoute(HttpMethod httpMethod, Pattern urlPattern, String methodToCall) {
        Map<Pattern, String> patternsOfMethod = this.binder.get(httpMethod);
        if (patternsOfMethod == null) patternsOfMethod = new HashMap<>();

        patternsOfMethod.put(urlPattern, methodToCall);
        this.binder.put(httpMethod, patternsOfMethod);

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

        Optional<UrlAction> urlActionOptional = getUrlAction(httpMethod, path);

        if (urlActionOptional.isPresent()) {
            UrlAction urlAction = urlActionOptional.get();
            introspectMethod(urlAction.getMethodName(), request, response, urlAction.getParameters());
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private Optional<UrlAction> getUrlAction(HttpMethod httpMethod, String path) {
        return binder.get(httpMethod)
                .entrySet()
                .stream()
                .filter(e -> e.getKey().matcher(path).matches())
                .map(e -> {
                    Pattern pattern = e.getKey();
                    Matcher patternMatcher = pattern.matcher(path);
                    String methodName = e.getValue();

                    patternMatcher.matches(); // MANDATORY (TOREVIEW)

                    Map<String, String> parameters = new HashMap<>();
                    getNamedGroupCandidates(pattern.pattern())
                            .forEach(groupName -> parameters.put(groupName, patternMatcher.group(groupName)));

                    return new UrlAction(methodName, parameters);
                })
                .findFirst();
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
            Method method = servlet.getClass().getDeclaredMethod(methodName, argsClasses);
            method.setAccessible(true);
            method.invoke(servlet, args);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            success = false;
        }

        return success;
    }

}
