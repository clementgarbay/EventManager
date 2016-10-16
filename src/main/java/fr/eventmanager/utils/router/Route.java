package fr.eventmanager.utils.router;

import java.util.regex.Pattern;

/**
 * @author Clément Garbay
 */
public class Route {

    private String routeBase;
    private Pattern pattern;
    private String methodNameToCall;
    private boolean isProtected;

    public Route(String routeBase, Pattern pattern, String methodNameToCall, boolean isProtected) {
        this.routeBase = routeBase;
        this.pattern = pattern;
        this.methodNameToCall = methodNameToCall;
        this.isProtected = isProtected;
    }

    public Route(String routeBase, Pattern pattern, String methodNameToCall) {
        this(routeBase, pattern, methodNameToCall, true);
    }

    boolean matchPattern(String str) {
        return pattern.matcher(str).matches();
    }

    Pattern getPattern() {
        return pattern;
    }

    String getMethodNameToCall() {
        return methodNameToCall;
    }

    public boolean isProtected() {
        return isProtected;
    }

    public String getFullRoute() {
        return routeBase + pattern.pattern();
    }

    public enum RouteId {
        LOGIN, LOGOUT, EVENTS, EVENT
    }
}

