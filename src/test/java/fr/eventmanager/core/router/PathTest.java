package fr.eventmanager.core.router;

import org.junit.Test;

import java.util.Collections;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author Clément Garbay
 */
public class PathTest {

    @Test
    public void getPathBase() throws Exception {
        Path path = Path.LOGIN;
        assertEquals(path.getPathBase(), Path.PathConstants.AUTH);
    }

    @Test
    public void getPathExtension() throws Exception {
        Path path = Path.LOGIN;
        assertEquals(path.getPathExtension().pattern(), "/login");
    }

    @Test
    public void getFullPath() throws Exception {
        Path path = Path.SIGNUP;
        assertEquals(path.getFullPath(), "/auth/signup");
    }

    @Test
    public void getFullPathWithParameters() throws Exception {
        Path path = Path.EVENT_EDIT;
        Map<String, String> params = Collections.singletonMap("eventId", "2");

        assertEquals(path.getFullPath(params), "/events/2/edit");
    }

    @Test
    public void extractParametersOf() throws Exception {
        Path path = Path.EVENT;
        String url = "/2";
        Map<String, String> expected = Collections.singletonMap("eventId", "2");

        assertEquals(path.extractParametersOf(url), expected);
    }

}