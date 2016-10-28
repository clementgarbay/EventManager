package fr.eventmanager.core.router;

/**
 * @author Clément Garbay
 */
public class Route {

    private HttpMethod httpMethod;
    private Path path;
    private boolean isProtected;

    private ServletConsumer<WrappedHttpServlet> consumer;

    public Route(HttpMethod httpMethod, Path path, boolean isProtected, ServletConsumer<WrappedHttpServlet> consumer) {
        this.httpMethod = httpMethod;
        this.path = path;
        this.isProtected = isProtected;
        this.consumer = consumer;
    }

    public boolean matchRoute(String str) {
        return path.getPathExtension().matcher(str).matches();
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public Path getPath() {
        return path;
    }

    public boolean isProtected() {
        return isProtected;
    }

    public ServletConsumer<WrappedHttpServlet> getConsumer() {
        return consumer;
    }

}

