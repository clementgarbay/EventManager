package fr.eventmanager.core.utils;

import static java.util.Objects.isNull;

/**
 * @author Clément Garbay
 */
public class Utils {

    /**
     * Simple remplacement of the coalescing operator.
     * https://en.wikipedia.org/wiki/Null_coalescing_operator
     *
     * @param value         The value to return if it is not null
     * @param defaultValue  The default value to return if the value is null
     * @return
     */
    public static <T> T ifNull(T value, T defaultValue) {
        return isNull(value) ? defaultValue : value;
    }
}
