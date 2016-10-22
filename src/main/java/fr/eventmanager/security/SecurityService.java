package fr.eventmanager.security;

import fr.eventmanager.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SecurityService {

    private static final String LOGGED_USER = "LOGGED_USER";

    public SecurityService() {}

    public static void setLoggedUser(HttpServletRequest request, User userConnected) {
        HttpSession session = request.getSession(false);
        if (session == null) session = request.getSession();
        session.setAttribute(LOGGED_USER, userConnected);
    }

    public static User getLoggedUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            Object connectedUserObject = session.getAttribute(LOGGED_USER);

            if (connectedUserObject instanceof User) {
                return (User) connectedUserObject;
            }
        }

        return null;
    }

    public static boolean isLogged(HttpServletRequest request) {
        return getLoggedUser(request) != null;
    }

    public static void clear(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) session.invalidate();
    }

}
