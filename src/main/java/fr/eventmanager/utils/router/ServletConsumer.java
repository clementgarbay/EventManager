package fr.eventmanager.utils.router;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @author Clément Garbay
 */
@FunctionalInterface
public interface ServletConsumer<T> {
    void accept(T t) throws ServletException, IOException;
}