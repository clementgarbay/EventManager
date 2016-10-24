package fr.eventmanager.entity;

import fr.eventmanager.utils.Field;

import java.util.List;

/**
 * @author Clément Garbay
 */
public interface StorableEntity {
    Integer getId();
    List<Field> getFields();
    static String getTableName() {
        return "";
    }
}
