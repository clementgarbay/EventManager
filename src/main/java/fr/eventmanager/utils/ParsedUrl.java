package fr.eventmanager.utils;

/**
 * @author Clément Garbay
 *
 * URL are divided in different parts.
 * For instance, "https://eventmanager.fr/events/subscribe/2" will be cut in :
 *  - "events" the controller name
 *  - "subscribe" the action name
 *  - "2" the parameter name
 *
 * TODO: There is incorrect semantic meaning when the URL is "https://eventmanager.fr/events/2" (to display the event number 2 for example) because "2" will be in action name attribute whereas it is (in this case) a parameter.
 * TODO: -> Review attributes names for semantic consistency ?
 */
public class ParsedUrl {

    private String baseUrl;

    private String domainName;
    private String controllerName;
    private String actionName;
    private String parameterName;

    private final static String DEFAULT_DOMAIN_NAME = "";
    private final static String DEFAULT_CONTROLLER_NAME = "";
    private final static String DEFAULT_ACTION_NAME = "default";
    private final static String DEFAULT_PARAMETER_NAME = "";

    public ParsedUrl(String baseUrl) {
        this.baseUrl = baseUrl;

        this.domainName = DEFAULT_DOMAIN_NAME;
        this.controllerName = DEFAULT_CONTROLLER_NAME;
        this.actionName = DEFAULT_ACTION_NAME;
        this.parameterName = DEFAULT_PARAMETER_NAME;

        // Parse URL string
        String[] splitUrl = baseUrl.split("//");
        if (splitUrl.length > 1) {
            String[] splitUrlWithoutProcotol = splitUrl[1].split("/");
            if (splitUrlWithoutProcotol.length >= 1) this.domainName = splitUrlWithoutProcotol[0];
            if (splitUrlWithoutProcotol.length >= 2) this.controllerName = splitUrlWithoutProcotol[1];
            if (splitUrlWithoutProcotol.length >= 3) this.actionName = splitUrlWithoutProcotol[2];
            if (splitUrlWithoutProcotol.length >= 4) this.parameterName = splitUrlWithoutProcotol[3];
        }
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getDomainName() {
        return domainName;
    }

    public String getControllerName() {
        return controllerName;
    }

    public String getActionName() {
        return actionName;
    }

    public String getParameterName() {
        return parameterName;
    }
}
