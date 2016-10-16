package fr.eventmanager.controller;

import fr.eventmanager.dao.impl.UserSampleDAO;
import fr.eventmanager.service.IUserService;
import fr.eventmanager.service.impl.UserService;
import fr.eventmanager.utils.Alert;
import fr.eventmanager.utils.HttpMethod;
import fr.eventmanager.utils.router.Route;
import fr.eventmanager.utils.router.ServletRouter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * @author Clément Garbay
 * @author Paul Defois
 */
@WebServlet(name = "LoginServlet", urlPatterns = {LoginServlet.ROUTE_BASE + "/*"})
public class LoginServlet extends Servlet {

    static final String ROUTE_BASE = "/app";

    private IUserService userService;

    @Override
    public void init() throws ServletException {
        super.init();

        this.userService = new UserService(new UserSampleDAO());

        registerRoute(HttpMethod.GET, new Route(ROUTE_BASE, Pattern.compile("/"), "displayLoginPage"));
        registerRoute(HttpMethod.POST, new Route(ROUTE_BASE, Pattern.compile("/login"), "login"));
        registerRoute(HttpMethod.GET, new Route(ROUTE_BASE, Pattern.compile("/logout"), "logout"));
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
