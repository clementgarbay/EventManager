package fr.eventmanager.security;

import fr.eventmanager.core.session.SessionManager;
import fr.eventmanager.entity.User;

import javax.servlet.http.HttpServletRequest;

import static java.util.Objects.isNull;

public class SecurityService {

    private static final String LOGGED_USER = "LOGGED_USER";

    public static void setLoggedUser(HttpServletRequest request, User userConnected) {
        SessionManager.set(request, LOGGED_USER, userConnected);
    }

    public static User getLoggedUser(HttpServletRequest request) {
        return SessionManager.get(request, LOGGED_USER);
    }

    public static boolean isLogged(HttpServletRequest request) {
        return !isNull(getLoggedUser(request));
    }

    public static void clear(HttpServletRequest request) {
        SessionManager.clear(request);
    }

}
