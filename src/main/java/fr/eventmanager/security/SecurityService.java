package fr.eventmanager.security;

import fr.eventmanager.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SecurityService {
    public static final String SECURITY_SERVICE = "SECURITY_SERVICE";

    public static final String SECURITY_IS_LOGGED = "IS_LOGGED";
    private User userConnected;

    public SecurityService() {
        this.userConnected = new User("unknow@unknow.com", "unknow");
    }

    public boolean isLoggedIn(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        boolean isLoggedIn = false;
        if(session.getAttribute(SECURITY_IS_LOGGED) == null) {
            session.setAttribute(SECURITY_IS_LOGGED, false);
        } else {
            isLoggedIn = (boolean) session.getAttribute(SECURITY_IS_LOGGED);
        }

        return isLoggedIn && userConnected.getConnected();
    }

    public void setLogged(boolean isLogged, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        session.setAttribute(SECURITY_IS_LOGGED, isLogged);
        userConnected.setConnected(isLogged);
    }

    public User getUserConnected() {
        return userConnected;
    }

    public void setUserConnected(User userConnected) {
        this.userConnected = userConnected;
    }
}
