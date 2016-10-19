package fr.eventmanager.security;

import fr.eventmanager.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SecurityService {

    public static final String SECURITY_SERVICE = "SECURITY_SERVICE";
    public static final String SECURITY_IS_LOGGED = "IS_LOGGED";

    private User userConnected;

    public SecurityService() {}

    public boolean isLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession();

        boolean isLoggedIn = false;
        if(session.getAttribute(SECURITY_IS_LOGGED) == null) {
            session.setAttribute(SECURITY_IS_LOGGED, false);
        } else {
            isLoggedIn = (boolean) session.getAttribute(SECURITY_IS_LOGGED);
        }

        return isLoggedIn && userConnected.isRegistered();
    }

    public void setLogged(HttpServletRequest request, boolean logged, User user) {
        HttpSession session = request.getSession();

        session.setAttribute(SECURITY_IS_LOGGED, logged);
        userConnected = user;
    }

    public void clear(HttpServletRequest request) {
        setLogged(request, false, null);
    }

    public User getUserConnected() {
        return userConnected;
    }

}
