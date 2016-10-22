package fr.eventmanager.utils.validator;

/**
 * @author Clément Garbay
 */
public interface ValidatableEntity<T extends ValidatableEntity> {
    EitherValidatorResult validate();
}
