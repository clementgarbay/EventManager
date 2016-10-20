package fr.eventmanager.entity;

/**
 * @author Clément Garbay
 */
public interface StorableEntity {
    Integer getId();
    static String getTableName() {
        return "";
    }
}
