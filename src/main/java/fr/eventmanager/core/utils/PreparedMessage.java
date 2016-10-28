package fr.eventmanager.core.utils;

/**
 * @author Clément Garbay
 */
public enum PreparedMessage {
    VALIDATION_IS_EMPTY("Le champ %s ne peut pas être vide."),
    VALIDATION_ARE_EMPTY("Tous les champs doivent être remplis."),
    VALIDATION_IS_INCORRECT("Le champ %s est incorrect."),
    NOT_FOUND("La page demandée est introuvable."),
    UNAUTHORIZED("Veuillez vous connecter pour accéder à cette ressource."),
    FORBIDDEN("Vous n'avez pas les droits nécessaires pour accéder à cette ressource."),
    INTERNAL_SERVER_ERROR("Une erreur est survenue. Merci de réessayer.");

    private final String message;

    PreparedMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getMessage(Object... args) {
        return String.format(message, args);
    }
}
