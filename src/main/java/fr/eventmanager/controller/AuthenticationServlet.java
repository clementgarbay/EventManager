package fr.eventmanager.controller;

import fr.eventmanager.dao.impl.UserSampleDAO;
import fr.eventmanager.service.IUserService;
import fr.eventmanager.service.impl.UserService;
import fr.eventmanager.utils.Alert;
import fr.eventmanager.utils.HttpMethod;
import fr.eventmanager.utils.router.RouteId;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * @author Clément Garbay
 * @author Paul Defois
 */
public class AuthenticationServlet extends Servlet {

    static final String ROUTE_BASE = "/auth";

    private IUserService userService;

    @Override
    public void init() throws ServletException {
        super.init();

        this.userService = new UserService(new UserSampleDAO());

        super.servletRouter
                .bind(RouteId.LOGIN, HttpMethod.GET, ROUTE_BASE, Pattern.compile("/login"), false)
                    .to(this, "displayLoginPage")
                .bind(RouteId.LOGIN, HttpMethod.POST, ROUTE_BASE, Pattern.compile("/login"), false)
                    .to(this, "login")
                .bind(RouteId.LOGOUT, HttpMethod.GET, ROUTE_BASE, Pattern.compile("/logout"), false)
                    .to(this, "logout");
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
