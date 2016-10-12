package fr.eventmanager.utils.router;

import java.util.regex.Pattern;

/**
 * @author Clément Garbay
 */
public class Route {

    private Pattern pattern;
    private String methodNameToCall;

    public Route(Pattern pattern, String methodNameToCall) {
        this.pattern = pattern;
        this.methodNameToCall = methodNameToCall;
    }

    boolean matchPattern(String str) {
        return pattern.matcher(str).matches();
    }

    Pattern getPattern() {
        return pattern;
    }

    String getMethodNameToCall() {
        return methodNameToCall;
    }
}
