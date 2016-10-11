package fr.eventmanager.controller;

import fr.eventmanager.utils.router.HttpMethod;
import fr.eventmanager.utils.router.ServletRouter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author Clément Garbay
 */
public class LoginServlet extends Servlet {

    @Override
    public void init() throws ServletException {
        super.init();

        super.servletRouter = new ServletRouter(this)
                .registerRouter(HttpMethod.GET, Pattern.compile("/"), "login")
                .registerRouter(HttpMethod.POST, Pattern.compile("/"), "login");
    }

    private void login(HttpServletRequest request,  HttpServletResponse response, String path, Map<String, String> parameters) throws ServletException, IOException {
        render("login.jspf", request, response);
    }

}
