package fr.eventmanager.utils.router;

import fr.eventmanager.controller.Servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Clément Garbay
 */
public class ServletRouter {

    private Servlet servlet;
    private Map<HttpMethod, Map<Pattern, String>> binder;

    public ServletRouter(Servlet servlet) {
        this.servlet = servlet;
        this.binder = new HashMap<>();
    }

    public ServletRouter registerRouter(HttpMethod httpMethod, Pattern urlPattern, String methodToCall) {
        Map<Pattern, String> patternsOfMethod = this.binder.get(httpMethod);
        if (patternsOfMethod == null) patternsOfMethod = new HashMap<>();

        patternsOfMethod.put(urlPattern, methodToCall);
        this.binder.put(httpMethod, patternsOfMethod);

        return this;
    }

    public void process(HttpMethod httpMethod, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathInfo = request.getPathInfo();
        String path = (pathInfo != null) ? pathInfo : "/";

        Optional<UrlAction> urlActionOptional = getMatchingMethod(httpMethod, path);

        if (urlActionOptional.isPresent()) {
            UrlAction urlAction = urlActionOptional.get();
            introspectMethod(urlAction.getMethodName(), request, response, path, urlAction.getParameters());
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private Optional<UrlAction> getMatchingMethod(HttpMethod httpMethod, String path) {
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

    private void introspectMethod(String methodName, HttpServletRequest request, HttpServletResponse response, String path, Map<String, String> parameters) throws IOException {
        Class<?>[] argsClasses = {HttpServletRequest.class, HttpServletResponse.class, String.class, Map.class};

        try {
            Method method = servlet.getClass().getDeclaredMethod(methodName, argsClasses);
            method.setAccessible(true);
            method.invoke(servlet, request, response, path, parameters);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

}
