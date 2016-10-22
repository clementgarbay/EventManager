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
public class EitherValidatorResult<T extends ValidatableEntity> {

    private T entity;

    private Optional<ValidationMessage> success;
    private Optional<ValidationMessage> error;

    private EitherValidatorResult(T entity, Optional<ValidationMessage> success, Optional<ValidationMessage> error) {
        this.entity = entity;
        this.success = success;
        this.error = error;
    }

    public static <T extends ValidatableEntity> EitherValidatorResult<T> success(T entity, ValidationMessage success) {
        return new EitherValidatorResult<>(entity, Optional.of(success), Optional.empty());
    }

    public static <T extends ValidatableEntity> EitherValidatorResult<T> success(T entity) {
        return new EitherValidatorResult<>(entity, Optional.of(new ValidationMessage()), Optional.empty());
    }

    public static <T extends ValidatableEntity> EitherValidatorResult<T> error(T entity, ValidationMessage error) {
        return new EitherValidatorResult<>(entity, Optional.empty(), Optional.of(error));
    }

    public void apply(Consumer<? super ValidatorResult<T>> successConsumer, Consumer<? super ValidatorResult<T>> errorConsumer) {
        if (success.isPresent()) successConsumer.accept(new ValidatorResult<>(entity, success.get()));
        if (error.isPresent()) errorConsumer.accept(new ValidatorResult<>(entity, error.get()));
    }
}
