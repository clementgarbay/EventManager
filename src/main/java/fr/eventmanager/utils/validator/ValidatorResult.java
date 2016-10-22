package fr.eventmanager.utils.validator;

/**
 * @author clementgarbay
 */
public class ValidatorResult<T> {

    private T entity;
    private Message message;

    public ValidatorResult(T entity, Message message) {
        this.entity = entity;
        this.message = message;
    }

    public T getEntity() {
        return entity;
    }

    public String getMessage() {
        return message.getMessage();
    }
}
