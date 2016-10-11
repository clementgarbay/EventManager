package fr.eventmanager.utils.router;

import java.util.Map;

/**
 * @author Clément Garbay
 */
class UrlAction {

    private String methodName;
    private Map<String, String> parameters; // Map<parameterId, parameterValue>

    UrlAction(String methodName, Map<String, String> parameters) {
        this.methodName = methodName;
        this.parameters = parameters;
    }

    String getMethodName() {
        return methodName;
    }

    Map<String, String> getParameters() {
        return parameters;
    }
}
