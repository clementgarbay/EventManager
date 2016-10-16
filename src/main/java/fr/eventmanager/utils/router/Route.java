package fr.eventmanager.utils.router;

import fr.eventmanager.controller.Servlet;

import java.util.regex.Pattern;

/**
 * @author Clément Garbay
 */
public class Route {

    private RouteId routeId;
    private String pathBase;
    private Pattern pathExtension;
    private boolean isProtected;

    private Servlet servletToCall;
    private String methodNameToCall;

    public Route(RouteId routeId, String pathBase, Pattern pathExtension, boolean isProtected, Servlet servletToCall, String methodNameToCall) {
        this.routeId = routeId;
        this.pathBase = pathBase;
        this.pathExtension = pathExtension;
        this.isProtected = isProtected;
        this.servletToCall = servletToCall;
        this.methodNameToCall = methodNameToCall;
    }

    public RouteId getRouteId() {
        return routeId;
    }

    public boolean matchRoute(String str) {
        return pathExtension.matcher(str).matches();
    }

    public Pattern getPathExtension() {
        return pathExtension;
    }

    public Servlet getServletToCall() {
        return servletToCall;
    }

    public String getMethodNameToCall() {
        return methodNameToCall;
    }

    public boolean isProtected() {
        return isProtected;
    }

    public String getFullPath() {
        return pathBase + pathExtension.pattern();
    }


}

