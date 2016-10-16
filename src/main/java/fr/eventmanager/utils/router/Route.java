package fr.eventmanager.utils.router;

import fr.eventmanager.utils.HttpMethod;

/**
 * @author Clément Garbay
 */
public class Route {

    private HttpMethod httpMethod;
    private Path path;
    private boolean isProtected;

    private String methodNameToCall;

    public Route(HttpMethod httpMethod, Path path, boolean isProtected, String methodNameToCall) {
        this.httpMethod = httpMethod;
        this.path = path;
        this.isProtected = isProtected;
        this.methodNameToCall = methodNameToCall;
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

    public String getMethodNameToCall() {
        return methodNameToCall;
    }

}

