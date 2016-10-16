package fr.eventmanager.controller;

import fr.eventmanager.dao.impl.UserSampleDAO;
import fr.eventmanager.service.IUserService;
import fr.eventmanager.service.impl.UserService;
import fr.eventmanager.utils.HttpMethod;
import fr.eventmanager.utils.router.Route;
import fr.eventmanager.utils.router.ServletRouter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * @author Clément Garbay
 */
public class LoginServlet extends Servlet {
    private IUserService userService;

    @Override
    public void init() throws ServletException {
        super.init();

        this.userService = new UserService(new UserSampleDAO());

        super.servletRouter = new ServletRouter(this)
                .registerRoute(HttpMethod.GET, new Route(Pattern.compile("/"), "displayLoginPage"))
                .registerRoute(HttpMethod.POST, new Route(Pattern.compile("/"), "login"));
    }

    private void displayLoginPage(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException {
        render("login.jsp", request, response);
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        boolean isLoggedIn = false;
        if(session.getAttribute("IS_LOGGED") == null) {
            session.setAttribute("IS_LOGGED", false);
        } else {
            isLoggedIn = (boolean) session.getAttribute("IS_LOGGED");
        }

        boolean userExist = userService.isUserExists(request.getParameter("user_email"));

        request.setAttribute("USER_EXISTS", userExist ? "OUI" : "NON");

        render("login.jsp", request, response);
    }

}
