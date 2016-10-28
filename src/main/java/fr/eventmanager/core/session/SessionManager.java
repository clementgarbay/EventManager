package fr.eventmanager.core.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static java.util.Objects.isNull;

/**
 * @author Clément Garbay
 *
 * @implNote Singleton
 */
public class SessionManager {

    public static void set(HttpServletRequest request, String key, Object value) {
        HttpSession session = request.getSession(false);
        if (session == null) session = request.getSession();
        session.setAttribute(key, value);
    }

    public static <T> T get(HttpServletRequest request, String key) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            Object value = session.getAttribute(key);
            return isNull(value) ? null : (T) value;
        }
        return null;
    }

    public static void remove(HttpServletRequest request, String key) {
        HttpSession session = request.getSession(false);
        if (session != null) session.removeAttribute(key);
    }

    public static void clear(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) session.invalidate();
    }

}
