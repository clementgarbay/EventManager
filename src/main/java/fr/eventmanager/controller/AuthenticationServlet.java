package fr.eventmanager.controller;

import fr.eventmanager.dao.impl.UserDAO;
import fr.eventmanager.entity.User;
import fr.eventmanager.security.SecurityService;
import fr.eventmanager.service.IUserService;
import fr.eventmanager.service.impl.UserService;
import fr.eventmanager.utils.Alert;
import fr.eventmanager.utils.router.HttpMethod;
import fr.eventmanager.utils.router.Path;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * @author Clément Garbay
 * @author Paul Defois
 */
@WebServlet(name = "AuthenticationServlet", urlPatterns = {Path.PathConstants.AUTH + "/*"})
public class AuthenticationServlet extends Servlet {

    private IUserService userService;

    @Override
    public void init() throws ServletException {
        super.init();

        this.userService = new UserService(new UserDAO());

        bind(HttpMethod.GET, Path.LOGIN, false).to("displayLoginPage");
        bind(HttpMethod.POST, Path.LOGIN, false).to("login");
        bind(HttpMethod.GET, Path.LOGOUT, false).to("logout");
    }

    private void displayLoginPage(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException {
        if (!SecurityService.isLogged(request)) {
            render("login.jsp", request, response);
        } else {
            render("events.jsp", request, response);
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("user_email");
        String password = request.getParameter("user_password");

        if (userService.areCredentialsValid(email, password)) {
            Optional<User> userOptional = userService.getUserByEmail(email);

            if (userOptional.isPresent()) { // useless ?
                SecurityService.setLoggedUser(request, userOptional.get());
                render("events.jsp", request, response);
            } else {
                render("login.jsp", request, response, new Alert(Alert.AlertType.DANGER, "Utilisateur introuvable."));
            }
        } else {
            render("login.jsp", request, response, new Alert(Alert.AlertType.DANGER, "Couple email / mot de passe incorrect."));
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SecurityService.clear(request);
        render("login.jsp", request, response, new Alert(Alert.AlertType.SUCCESS, "Déconnecté"));
    }

}
