package fr.eventmanager.core.validator;

import java.io.IOException;
import java.util.Optional;

/**
 * @author Clément Garbay
 *
 * @implNote Use Either pattern.
 */
public class EitherValidatorResult {

    private Optional<String> success;
    private Optional<String> error;

    private EitherValidatorResult(Optional<String> success, Optional<String> error) {
        this.success = success;
        this.error = error;
    }

    public static EitherValidatorResult success(String success) {
        return new EitherValidatorResult(Optional.of(success), Optional.empty());
    }

    public static EitherValidatorResult success() {
        return new EitherValidatorResult(Optional.of(""), Optional.empty());
    }

    public static EitherValidatorResult error(String error) {
        return new EitherValidatorResult(Optional.empty(), Optional.of(error));
    }

    public void apply(ValidationConsumer<? super String> successConsumer, ValidationConsumer<? super String> errorConsumer) throws IOException {
        if (success.isPresent()) successConsumer.accept(success.get());
        if (error.isPresent()) errorConsumer.accept(error.get());
    }
}
