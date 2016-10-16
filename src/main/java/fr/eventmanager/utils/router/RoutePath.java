package fr.eventmanager.utils.router;

import fr.eventmanager.controller.Servlet;
import fr.eventmanager.utils.HttpMethod;

import java.util.regex.Pattern;

/**
 * @author Clément Garbay
 */
public class RoutePath {

    private ServletRouter servletRouter;

    private HttpMethod httpMethod;
    private RouteId routeId;
    private String pathBase;
    private Pattern pathExtension;
    private boolean isProtected;

    public RoutePath(ServletRouter servletRouter, HttpMethod httpMethod, RouteId routeId, String pathBase, Pattern pathExtension, boolean isProtected) {
        this.servletRouter = servletRouter;
        this.httpMethod = httpMethod;
        this.routeId = routeId;
        this.pathBase = pathBase;
        this.pathExtension = pathExtension;
        this.isProtected = isProtected;
    }

    public ServletRouter to(Servlet servlet, String methodNameToCall) {
        Route newRoute = new Route(routeId, pathBase, pathExtension, isProtected, servlet, methodNameToCall);
        return servletRouter.registerRoute(httpMethod, newRoute);
    }
}
