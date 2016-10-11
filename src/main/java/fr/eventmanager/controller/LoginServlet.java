package fr.eventmanager.controller;

import fr.eventmanager.utils.HttpMethod;
import fr.eventmanager.utils.router.ServletRouter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * @author Clément Garbay
 */
public class LoginServlet extends Servlet {

    @Override
    public void init() throws ServletException {
        super.init();

        super.servletRouter = new ServletRouter(this)
                .registerRoute(HttpMethod.GET, Pattern.compile("/"), "login")
                .registerRoute(HttpMethod.POST, Pattern.compile("/"), "login");
    }

    private void login(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException {
        render("login.jspf", request, response);
    }

}
