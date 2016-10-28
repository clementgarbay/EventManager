package fr.eventmanager.core.utils;

import fr.eventmanager.core.utils.type.Tuple;

/**
 * @author Clément Garbay
 */
public class Field<T> extends Tuple<String,T> {

    public Field(String name, T value) {
        super(name, value);
    }

    public String getName() {
        return super.getValue1();
    }

    public T getValue() {
        return super.getValue2();
    }
}
