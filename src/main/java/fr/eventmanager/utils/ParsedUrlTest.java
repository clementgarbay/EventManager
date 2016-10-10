package fr.eventmanager.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Clément Garbay
 */
public class ParsedUrlTest {

    private final static String DOMAIN = "eventmanager.fr";
    private final static String CONTROLLER = "controller";
    private final static String ACTION = "action";
    private final static String PARAMETER = "2";
    private final static String BASE_URL = String.format("https://%s/%s/%s/%s", DOMAIN, CONTROLLER, ACTION, PARAMETER);

    @Test
    public void getBaseUrl() throws Exception {
        ParsedUrl parsedUrl = new ParsedUrl(BASE_URL);
        assertEquals(BASE_URL, parsedUrl.getBaseUrl());
    }

    @Test
    public void getDomainName() throws Exception {
        ParsedUrl parsedUrl = new ParsedUrl("");
        assertEquals("", parsedUrl.getDomainName());
    }

    @Test
    public void getDomainName2() throws Exception {
        ParsedUrl parsedUrl = new ParsedUrl(BASE_URL);
        assertEquals(DOMAIN, parsedUrl.getDomainName());
    }

    @Test
    public void getControllerName() throws Exception {
        ParsedUrl parsedUrl = new ParsedUrl(BASE_URL);
        assertEquals(CONTROLLER, parsedUrl.getControllerName());
    }

    @Test
    public void getActionName() throws Exception {
        ParsedUrl parsedUrl = new ParsedUrl(BASE_URL);
        assertEquals(ACTION, parsedUrl.getActionName());
    }

    @Test
    public void getActionName2() throws Exception {
        ParsedUrl parsedUrl = new ParsedUrl(String.format("https://%s/%s/", DOMAIN, CONTROLLER));
        assertEquals("default", parsedUrl.getActionName());
    }

    @Test
    public void getParameterName() throws Exception {
        ParsedUrl parsedUrl = new ParsedUrl(BASE_URL);
        assertEquals(PARAMETER, parsedUrl.getParameterName());
    }
}