package fr.eventmanager.core.persistence;

import fr.eventmanager.core.utils.Field;

/**
 * @author Clément Garbay
 *
 * @implNote Field with operator applied between name and value when proceed to query. By default, the operator is EQUAL.
 */
public class QueryField<T> extends Field<T> {

    public enum Operator { EQUAL, LIKE }

    private Operator filter;

    public QueryField(String name, T value, Operator filter) {
        super(name, value);
        this.filter = filter;
    }

    public QueryField(String name, T value) {
        this(name, value, Operator.EQUAL);
    }

    public Operator getFilter() {
        return filter;
    }
}
