package fr.eventmanager.utils;

import fr.eventmanager.utils.router.Route;

import java.util.HashMap;
import java.util.Map;

import static fr.eventmanager.utils.router.Route.*;
import static fr.eventmanager.controller.LoginServlet.*;
import static fr.eventmanager.controller.EventsServlet.*;

public class UrlUtils {
    public static final String URL_UTILS_SERVICE = "URL_UTILS_SERVICE";
    public static final String ROUTE_SERVICE = "ROUTE_SERVICE";
    private static Map<Route, String> URLs;

    public UrlUtils() {
        URLs = new HashMap<>();

        URLs.put(LOGIN, ROUTE_LOGIN);
        URLs.put(LOGOUT, ROUTE_LOGOUT);
        URLs.put(EVENTS, "/events" + ROUTE_EVENTS);
    }



    public static String getURL(Route route) {
        return URLs.get(route);
    }
}
