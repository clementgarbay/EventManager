package fr.eventmanager.utils.router;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Clément Garbay
 */
public enum Path {
    HOME (PathConstants.BLANK, Pattern.compile("/")),
    LOGIN (PathConstants.AUTH, Pattern.compile("/login")),
    LOGOUT (PathConstants.AUTH, Pattern.compile("/logout")),
    EVENTS (PathConstants.EVENTS, Pattern.compile("/")),
    EVENTS_NEW (PathConstants.EVENTS, Pattern.compile("/new")),
    EVENT (PathConstants.EVENTS, Pattern.compile("/(?<eventId>\\d+)")),
    EVENT_EDIT (PathConstants.EVENTS, Pattern.compile("/(?<eventId>\\d+)/edit")),
    EVENT_SUBSCRIBE (PathConstants.EVENTS, Pattern.compile("/(?<eventId>\\d+)/subscribe")),
    EVENT_UNSUBSCRIBE (PathConstants.EVENTS, Pattern.compile("/(?<eventId>\\d+)/unsubscribe")),
    PROFIL (PathConstants.PROFIL, Pattern.compile("/"));

    private final String pathBase;
    private final Pattern pathExtension;

    Path(final String pathBase, final Pattern pathExtension) {
        this.pathBase = pathBase;
        this.pathExtension = pathExtension;
    }

    public String getPathBase() {
        return pathBase;
    }

    public Pattern getPathExtension() {
        return pathExtension;
    }

    public String getFullPath() {
        return pathBase + pathExtension.pattern();
    }

    /**
     * Extract parameters in a path string from a corresponding path pattern.
     *
     * For instance, Path.EVENT.extractParametersOf("/events/2")
     * return a Map of {eventId: 2}
     */
    public Map<String, String> extractParametersOf(String pathStr) {
        Matcher patternMatcher = pathExtension.matcher(pathStr);

        patternMatcher.matches(); // MANDATORY (TOREVIEW)

        Map<String, String> parameters = new HashMap<>();
        getNamedGroupCandidates(pathExtension.pattern())
                .forEach(groupName -> parameters.put(groupName, patternMatcher.group(groupName)));

        return parameters;
    }

    private Set<String> getNamedGroupCandidates(String regex) {
        Set<String> namedGroups = new TreeSet<>();
        Matcher matcher = Pattern.compile("\\(\\?<([a-zA-Z][a-zA-Z0-9]*)>").matcher(regex);

        while (matcher.find()) {
            namedGroups.add(matcher.group(1));
        }

        return namedGroups;
    }

    public static class PathConstants {
        public static final String BLANK = "";
        public static final String EVENTS = "/events";
        public static final String AUTH = "/auth";
        public static final String PROFIL = "/profil";
    }
}
