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

    private Optional<ValidationMessage> success;
    private Optional<ValidationMessage> error;

    private EitherValidatorResult(Optional<ValidationMessage> success, Optional<ValidationMessage> error) {
        this.success = success;
        this.error = error;
    }

    public static <T extends ValidatableEntity> EitherValidatorResult<T> success(ValidationMessage success) {
        return new EitherValidatorResult<>(Optional.of(success), Optional.empty());
    }

    public static <T extends ValidatableEntity> EitherValidatorResult<T> success() {
        return new EitherValidatorResult<>(Optional.of(new ValidationMessage()), Optional.empty());
    }

    public static <T extends ValidatableEntity> EitherValidatorResult<T> error(ValidationMessage error) {
        return new EitherValidatorResult<>(Optional.empty(), Optional.of(error));
    }

    public void apply(Consumer<? super ValidationMessage> successConsumer, Consumer<? super ValidationMessage> errorConsumer) {
        success.ifPresent(successConsumer);
        error.ifPresent(errorConsumer);
    }
}
