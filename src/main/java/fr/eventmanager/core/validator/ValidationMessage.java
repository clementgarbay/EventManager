package fr.eventmanager.core.validator;

/**
 * @author Clément Garbay
 */
public class ValidationMessage {

    private String message;

    public ValidationMessage(String message) {
        this.message = message;
    }

    public ValidationMessage(ErrorMessage errorMessage, Object... args) {
        this(String.format(errorMessage.getMessage(), args));
    }

    public ValidationMessage() {
        this("");
    }

    public String getMessage() {
        return message;
    }

    public enum ErrorMessage {
        IS_EMPTY ("Le champ %s ne peut pas être vide."),
        ARE_EMPTY ("Tous les champs doivent être remplis."),
        IS_INCORRECT ("Le champ %s est incorrect.");

        private final String message;

        ErrorMessage(final String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
