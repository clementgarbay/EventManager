package fr.eventmanager.utils;

import fr.eventmanager.controller.EventsServlet;
import fr.eventmanager.controller.LoginServlet;
import fr.eventmanager.utils.router.Route;

import java.util.HashMap;
import java.util.Map;

import static fr.eventmanager.utils.router.Route.*;

public class URLUtils {
    public static final String URL_UTILS_SERVICE = "URL_UTILS_SERVICE";
    public static final String ROUTE_SERVICE = "ROUTE_SERVICE";
    private static Map<Route, String> URLs;

    public URLUtils() {
        URLs = new HashMap<>();

        URLs.put(LOGIN, LoginServlet.getFullRoute(LOGIN));
        URLs.put(LOGOUT, LoginServlet.getFullRoute(LOGOUT));
        URLs.put(EVENTS, EventsServlet.getFullRoute(EVENTS));
        URLs.put(EVENT, EventsServlet.getFullRoute(EVENT));
    }

    public static String getURL(Route route) {
        return URLs.get(route);
    }
}
