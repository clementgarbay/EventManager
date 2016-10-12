package fr.eventmanager.controller;

import fr.eventmanager.dao.impl.UserSampleDAOImpl;
import fr.eventmanager.service.UserService;
import fr.eventmanager.service.impl.UserServiceImpl;
import fr.eventmanager.utils.Alert;
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
    public static final String ROUTE_LOGIN = "/login";
    public static final String ROUTE_LOGOUT = "/logout";
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();

        this.userService = new UserServiceImpl(new UserSampleDAOImpl());

        super.servletRouter = new ServletRouter(this)
                .registerRoute(HttpMethod.GET, Pattern.compile(ROUTE_LOGIN), "displayLoginPage")
                .registerRoute(HttpMethod.POST, Pattern.compile(ROUTE_LOGIN), "login")
                .registerRoute(HttpMethod.GET, Pattern.compile(ROUTE_LOGOUT), "logout");
    }

    private void displayLoginPage(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Connecté : " + securityService.isLoggedIn(request, response));

        if(!securityService.isLoggedIn(request, response)) {
            render("login.jsp", request, response);
        } else {
            render("events.jsp", request, response);
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        securityService.setLogged(false, request, response);

        render("login.jsp", request, response, new Alert(Alert.AlertType.SUCCESS, "Logout Success !"));
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean userExist = userService.isUserExists(request.getParameter("user_email"));

        request.setAttribute("USER_EXISTS", userExist ? "OUI" : "NON");

        securityService.setLogged(true, request, response);

        render("events.jsp", request, response);
    }

}
