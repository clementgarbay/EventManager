package fr.eventmanager.core.utils;

/**
 * @author Clément Garbay
 */
public class Alert {

    private AlertType type;
    private String message;

    public Alert(AlertType type, String message) {
        this.type = type;
        this.message = message;
    }

    public AlertType getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    // Syntactic sugar
    public static Alert success(String message) {
        return new Alert(AlertType.DANGER, message);
    }
    public static Alert danger(String message) {
        return new Alert(AlertType.DANGER, message);
    }

    /**
     * AlertType enumeration
     */
    public enum AlertType {
        SUCCESS("success"),
        DANGER("danger");

        private final String type;

        AlertType(final String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return type;
        }
    }
}
