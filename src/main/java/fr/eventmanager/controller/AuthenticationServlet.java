package fr.eventmanager.controller;

import fr.eventmanager.core.router.HttpMethod;
import fr.eventmanager.core.router.Path;
import fr.eventmanager.core.router.WrappedHttpServlet;
import fr.eventmanager.core.security.SecurityService;
import fr.eventmanager.core.utils.Alert;
import fr.eventmanager.core.utils.PreparedMessage;
import fr.eventmanager.dao.impl.UserDAO;
import fr.eventmanager.entity.User;
import fr.eventmanager.entity.helper.UserHelper;
import fr.eventmanager.service.IUserService;
import fr.eventmanager.service.impl.UserService;

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
        bind(HttpMethod.GET, Path.LOGOUT, false).to(this::logout);
        bind(HttpMethod.GET, Path.SIGNUP, false).to(this::displaySignupPage);
        bind(HttpMethod.POST, Path.LOGIN, false).to(this::login);
        bind(HttpMethod.POST, Path.SIGNUP, false).to(this::signup);
    }

    @Override
    public void destroy() {
        super.destroy();
        this.userService.close();
    }

    private void displayLoginPage(WrappedHttpServlet wrappedHttpServlet) throws IOException {
        HttpServletRequest request = wrappedHttpServlet.getRequest();
        HttpServletResponse response = wrappedHttpServlet.getResponse();

        if (SecurityService.isLogged(request)) {
            redirect(request, response, Path.EVENTS.getFullPath());
            return;
        }

        render(request, response, "login.jsp");
    }

    private void displaySignupPage(WrappedHttpServlet wrappedHttpServlet) throws IOException {
        HttpServletRequest request = wrappedHttpServlet.getRequest();
        HttpServletResponse response = wrappedHttpServlet.getResponse();

        render(request, response, "signup.jsp");
    }

    private void login(WrappedHttpServlet wrappedHttpServlet) throws IOException {
        HttpServletRequest request = wrappedHttpServlet.getRequest();
        HttpServletResponse response = wrappedHttpServlet.getResponse();

        User user = UserHelper.build(request);

        if (!userService.areCredentialsValid(user.getEmail(), user.getPassword())) {
            request.setAttribute("user", new User(user.getEmail()));
            render(request, response, "login.jsp", Alert.danger("Couple email / mot de passe incorrect."));
            return;
        }

        Optional<User> userOptional = userService.getUserByEmail(user.getEmail());

        SecurityService.setLoggedUser(request, userOptional.get());

        redirect(request, response, Path.EVENTS.getFullPath());
    }

    private void logout(WrappedHttpServlet wrappedHttpServlet) throws IOException {
        HttpServletRequest request = wrappedHttpServlet.getRequest();
        HttpServletResponse response = wrappedHttpServlet.getResponse();

        SecurityService.clear(request);
        redirect(request, response, Path.LOGIN.getFullPath());
    }

    private void signup(WrappedHttpServlet wrappedHttpServlet) throws IOException {
        HttpServletRequest request = wrappedHttpServlet.getRequest();
        HttpServletResponse response = wrappedHttpServlet.getResponse();

        User userBuilt = UserHelper.build(request);
        String passwordConfirm = request.getParameter("password2");

        if (!userBuilt.getPassword().equals(passwordConfirm)) {
            request.setAttribute("user", userBuilt);
            render(request, response, "signup.jsp", Alert.danger("Les mots de passe ne sont pas identiques."));
            return;
        }

        userBuilt
            .validate()
            .apply(success -> {
                if (userService.register(userBuilt)) {
                    redirect(request, response, Path.LOGIN.getFullPath(), Alert.success("Inscription réussie."));
                } else {
                    request.setAttribute("user", userBuilt);
                    render(request, response, "signup.jsp", Alert.danger(PreparedMessage.INTERNAL_SERVER_ERROR.getMessage()));
                }
            }, error -> {
                request.setAttribute("user", userBuilt);
                render(request, response, "signup.jsp", Alert.danger(error));
            });
    }

}
