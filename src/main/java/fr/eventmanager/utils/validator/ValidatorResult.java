package fr.eventmanager.utils.validator;

/**
 * @author clementgarbay
 */
public class ValidatorResult<T> {

    private T entity;
    private ValidationMessage validationMessage;

    public ValidatorResult(T entity, ValidationMessage validationMessage) {
        this.entity = entity;
        this.validationMessage = validationMessage;
    }

    public T getEntity() {
        return entity;
    }

    public String getMessage() {
        return validationMessage.getMessage();
    }
}
