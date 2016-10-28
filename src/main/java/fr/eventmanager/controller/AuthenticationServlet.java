package fr.eventmanager.controller;

import fr.eventmanager.dao.impl.UserDAO;
import fr.eventmanager.entity.User;
import fr.eventmanager.security.SecurityService;
import fr.eventmanager.service.IUserService;
import fr.eventmanager.service.impl.UserService;
import fr.eventmanager.core.utils.Alert;
import fr.eventmanager.core.router.HttpMethod;
import fr.eventmanager.core.router.Path;
import fr.eventmanager.core.router.WrappedHttpServlet;

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

        bind(HttpMethod.GET, Path.LOGIN, false).to(this::displayLoginPage);
        bind(HttpMethod.POST, Path.LOGIN, false).to(this::login);
        bind(HttpMethod.GET, Path.LOGOUT, false).to(this::logout);
    }

    @Override
    public void destroy() {
        super.destroy();
        this.userService.close();
    }

    private void displayLoginPage(WrappedHttpServlet wrappedHttpServlet) throws IOException {
        HttpServletRequest request = wrappedHttpServlet.getRequest();
        HttpServletResponse response = wrappedHttpServlet.getResponse();

        if (!SecurityService.isLogged(request)) {
            render(request, response, "login.jsp");
        } else {
            render(request, response, "events.jsp");
        }
    }

    private void login(WrappedHttpServlet wrappedHttpServlet) throws IOException {
        HttpServletRequest request = wrappedHttpServlet.getRequest();
        HttpServletResponse response = wrappedHttpServlet.getResponse();

        String email = request.getParameter("user_email");
        String password = request.getParameter("user_password");

        if (userService.areCredentialsValid(email, password)) {
            Optional<User> userOptional = userService.getUserByEmail(email);

            if (userOptional.isPresent()) { // useless ?
                SecurityService.setLoggedUser(request, userOptional.get());

                redirect(request, response, Path.EVENTS.getFullPath());
            } else {
                render(request, response, "login.jsp", new Alert(Alert.AlertType.DANGER, "Utilisateur introuvable."));
            }
        } else {
            render(request, response, "login.jsp", new Alert(Alert.AlertType.DANGER, "Couple email / mot de passe incorrect."));
        }
    }

    private void logout(WrappedHttpServlet wrappedHttpServlet) throws IOException {
        HttpServletRequest request = wrappedHttpServlet.getRequest();
        HttpServletResponse response = wrappedHttpServlet.getResponse();

        SecurityService.clear(request);
        render(request, response, "login.jsp", new Alert(Alert.AlertType.SUCCESS, "Déconnecté"));
    }

}
