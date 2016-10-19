package fr.eventmanager.entity;

/**
 * @author Clément Garbay
 */
public interface StorableEntity {
    int getId();
    static String getTableName() {
        return "";
    }
}
