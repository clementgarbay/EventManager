package fr.eventmanager.entity;

/**
 * @author Clément Garbay
 */
public abstract class StorableEntity {
    abstract int getId();
    static String getTableName() {
        return "";
    }
}
