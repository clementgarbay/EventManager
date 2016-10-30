package fr.eventmanager.core.router;

import fr.eventmanager.controller.EventsServlet;
import org.json.HTTP;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * @author Clément Garbay
 */
public class ServletRouterTest {

    private ServletRouter servletRouter;

    @Before
    public void setUp() throws Exception {
        this.servletRouter = new EventsServlet();
    }

    @Test
    public void bind() throws Exception {
        Path path = Path.EVENTS;
        HttpMethod method = HttpMethod.GET;

        servletRouter.bind(method, path, false).to(null);
        List<Route> routes = servletRouter.routes.get(method);

        List<Route> filteredRoutes = routes.stream().filter(route -> route.getPath().equals(path)).collect(Collectors.toList());
        assertEquals(filteredRoutes.size(), 1);

        boolean isPresent = filteredRoutes.stream().anyMatch(route -> route.getPath().equals(path));
        assertTrue(isPresent);

        Route route = filteredRoutes.stream().findFirst().get();
        assertEquals(route.isProtected(), false);
    }

    @Test
    public void bind1() throws Exception {
        Path path = Path.LOGIN;
        HttpMethod method = HttpMethod.POST;

        servletRouter.bind(method, path).to(null);
        List<Route> routes = servletRouter.routes.get(method);

        List<Route> filteredRoutes = routes.stream().filter(route -> route.getPath().equals(path)).collect(Collectors.toList());
        assertEquals(filteredRoutes.size(), 1);

        boolean isPresent = filteredRoutes.stream().anyMatch(route -> route.getPath().equals(path));
        assertTrue(isPresent);

        Route route = filteredRoutes.stream().findFirst().get();
        assertEquals(route.isProtected(), true);
    }

}