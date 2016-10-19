package fr.eventmanager.dao;

/**
 * @author Clément Garbay
 */
public class DbField {

    private String fieldName;
    private Object fieldValue;

    public DbField(String fieldName, Object fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}
