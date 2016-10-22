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


    //    public boolean isLoggedIn(HttpServletRequest request) {
//        HttpSession session = request.getSession();
//
//        boolean isLoggedIn = false;
//        if(session.getAttribute(SECURITY_IS_LOGGED) == null) {
//            session.setAttribute(SECURITY_IS_LOGGED, false);
//        } else {
//            isLoggedIn = (boolean) session.getAttribute(SECURITY_IS_LOGGED);
//        }
//
//        return isLoggedIn && userConnected.isRegistered();
//    }
//
//    public void setLogged(HttpServletRequest request, User user) {
//        HttpSession session = request.getSession();
//
//        session.setAttribute(SECURITY_IS_LOGGED, true);
//        userConnected = user;
//    }
//
//    public void clear(HttpServletRequest request) {
//        setLogged(request, false, null);
//    }
//
//    public User getLoggedUser() {
//        return userConnected;
//    }

}
