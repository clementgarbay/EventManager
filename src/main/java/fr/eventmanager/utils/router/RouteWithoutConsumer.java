package fr.eventmanager.utils.router;

/**
 * @author Clément Garbay
 */
public class RouteWithoutConsumer {

    private ServletRouter servletRouter;

    private HttpMethod httpMethod;
    private Path path;
    private boolean isProtected;

    public RouteWithoutConsumer(ServletRouter servletRouter, HttpMethod httpMethod, Path path, boolean isProtected) {
        this.servletRouter = servletRouter;
        this.httpMethod = httpMethod;
        this.path = path;
        this.isProtected = isProtected;
    }

    public ServletRouter to(ServletConsumer<WrappedHttpServlet> consumer) {
        Route newRoute = new Route(httpMethod, path, isProtected, consumer);
        return servletRouter.registerRoute(httpMethod, newRoute);
    }
}
