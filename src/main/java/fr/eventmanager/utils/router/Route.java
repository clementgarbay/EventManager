package fr.eventmanager.utils.router;

import java.util.regex.Pattern;

/**
 * @author Clément Garbay
 */
public class Route {

    private Pattern pattern;
    private String methodNameToCall;
    private boolean isProtected;

    public Route(Pattern pattern, String methodNameToCall, boolean isProtected) {
        this.pattern = pattern;
        this.methodNameToCall = methodNameToCall;
        this.isProtected = isProtected;
    }

    public Route(Pattern pattern, String methodNameToCall) {
        this(pattern, methodNameToCall, true);
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

    public boolean isProtected() {
        return isProtected;
    }
}
