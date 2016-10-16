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
    HOME ("", Pattern.compile("/")),
    LOGIN ("/auth", Pattern.compile("/login")),
    LOGOUT ("/auth", Pattern.compile("/logout")),
    EVENTS ("/events", Pattern.compile("/")),
    EVENT ("/events", Pattern.compile("/(?<eventId>\\d+)")),
    PROFIL ("/profil", Pattern.compile("/"));

    private final String pathBase;
    private final Pattern pathExtension;

    Path(final String pathBase, Pattern pathExtension) {
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
}
