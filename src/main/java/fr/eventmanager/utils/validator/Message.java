package fr.eventmanager.utils.validator;

/**
 * @author Clément Garbay
 */
public class Message {

    private String message;

    public Message(String message) {
        this.message = message;
    }

    public Message(ErrorMessage errorMessage, Object... args) {
        this(String.format(errorMessage.getMessage(), args));
    }

    public Message() {
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
