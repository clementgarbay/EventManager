package fr.eventmanager.utils.router;

/**
 * @author Clément Garbay
 */
public class RouteWithoutMethod {

    private ServletRouter servletRouter;

    private HttpMethod httpMethod;
    private Path path;
    private boolean isProtected;

    public RouteWithoutMethod(ServletRouter servletRouter, HttpMethod httpMethod, Path path, boolean isProtected) {
        this.servletRouter = servletRouter;
        this.httpMethod = httpMethod;
        this.path = path;
        this.isProtected = isProtected;
    }

    public ServletRouter to(String methodNameToCall) {
        Route newRoute = new Route(httpMethod, path, isProtected, methodNameToCall);
        return servletRouter.registerRoute(httpMethod, newRoute);
    }
}
