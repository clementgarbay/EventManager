package fr.eventmanager.core.validator;

import java.io.IOException;

/**
 * @author Clément Garbay
 */
@FunctionalInterface
public interface ValidationConsumer<T> {
    void accept(T t) throws IOException;
}