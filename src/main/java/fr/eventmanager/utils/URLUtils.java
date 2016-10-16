package fr.eventmanager.utils;

import fr.eventmanager.utils.router.Route;

import java.util.HashMap;
import java.util.Map;

public class URLUtils {
    private static Map<Route, String> URLs;

    public URLUtils() {
        URLs = new HashMap<>();

//        URLs.put(LOGIN, LoginServlet.getFullRoute(LOGIN));
//        URLs.put(LOGOUT, LoginServlet.getFullRoute(LOGOUT));
//        URLs.put(EVENTS, EventsServlet.getFullRoute(EVENTS));
//        URLs.put(EVENT, EventsServlet.getFullRoute(EVENT));
    }

    public static String getURL(Route route) {
        return route.getFullPath();
    }
}
