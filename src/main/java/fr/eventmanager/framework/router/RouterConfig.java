package fr.eventmanager.framework.router;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Clément Garbay
 */
class RouterConfig {

    private static RouterConfig INSTANCE = new RouterConfig();

    private static final Map<String, String> binder;
    static {
        binder = new HashMap<>();
        binder.put("events", "EventsServlet");
    }

    static RouterConfig getInstance() { return INSTANCE; }

    Optional<String> getServletNameFor(String url) {
        String res = binder.get(url);
        return (res != null) ? Optional.of(res) : Optional.empty();
    }
}
