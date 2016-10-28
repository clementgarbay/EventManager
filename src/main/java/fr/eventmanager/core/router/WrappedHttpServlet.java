package fr.eventmanager.core.router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Map;

/**
 * @author Clément Garbay
 */
public class WrappedHttpServlet {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private Map<String, String> parameters;

    public WrappedHttpServlet(HttpServletRequest request, HttpServletResponse response, Map<String, String> parameters) {
        this.request = request;
        this.response = response;
        this.parameters = parameters;
    }

    public WrappedHttpServlet(HttpServletRequest request, HttpServletResponse response) {
        this(request, response, Collections.emptyMap());
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }
}
