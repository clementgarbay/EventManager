package fr.eventmanager.utils.validator;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * @author Clément Garbay
 *
 * @implNote Use Either pattern.
 *
 * TODO : Try to generify with Either type
 */
public class EitherValidator<T extends ValidatableEntity> {

    private T entity;

    private Optional<Message> success;
    private Optional<Message> error;

    private EitherValidator(T entity, Optional<Message> success, Optional<Message> error) {
        this.entity = entity;
        this.success = success;
        this.error = error;
    }

    public static <T extends ValidatableEntity> EitherValidator<T> success(T entity, Message success) {
        return new EitherValidator<>(entity, Optional.of(success), Optional.empty());
    }

    public static <T extends ValidatableEntity> EitherValidator<T> success(T entity) {
        return new EitherValidator<>(entity, Optional.of(new Message()), Optional.empty());
    }

    public static <T extends ValidatableEntity> EitherValidator<T> error(T entity, Message error) {
        return new EitherValidator<>(entity, Optional.empty(), Optional.of(error));
    }

    public void apply(Consumer<? super ValidatorResult<T>> successConsumer, Consumer<? super ValidatorResult<T>> errorConsumer) {
        if (success.isPresent()) successConsumer.accept(new ValidatorResult<>(entity, success.get()));
        if (error.isPresent()) errorConsumer.accept(new ValidatorResult<>(entity, error.get()));
    }
}
