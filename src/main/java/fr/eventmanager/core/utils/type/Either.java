package fr.eventmanager.core.utils.type;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author Clément Garbay
 */
public class Either<L, R> {

    private final Optional<L> left;
    private final Optional<R> right;

    public Either(Optional<L> left, Optional<R> right) {
        this.left = left;
        this.right = right;
    }

    public static <L,R> Either<L,R> left(L value) {
        return new Either<>(Optional.of(value), Optional.empty());
    }

    public static <L,R> Either<L,R> right(R value) {
        return new Either<>(Optional.empty(), Optional.of(value));
    }

    public <T> T map(Function<? super L, ? extends T> lFunc, Function<? super R, ? extends T> rFunc) {
        return left.map(lFunc).orElseGet(() -> right.map(rFunc).get());
    }

    public <T> Either<T,R> mapLeft(Function<? super L, ? extends T> lFunc) {
        return new Either<>(left.map(lFunc), right);
    }

    public <T> Either<L,T> mapRight(Function<? super R, ? extends T> rFunc) {
        return new Either<>(left, right.map(rFunc));
    }

    public void apply(Consumer<? super L> lFunc, Consumer<? super R> rFunc) {
        left.ifPresent(lFunc);
        right.ifPresent(rFunc);
    }
}