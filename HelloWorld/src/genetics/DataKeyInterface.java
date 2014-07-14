/*
 * Copyright Heathman Innovative Solutions Jul 12, 2014.
 */
package genetics;

import jade.core.behaviours.DataStore;

/**
 * Data store enumerations implement this interface to use the keys.
 *
 * @author Brad
 */
public interface DataKeyInterface {

    Class<?> getType();

    /**
     * Test that a value is valid for this enumeration key.
     * Returns false, if value is null.
     * 
     * @param value Value tested
     * @return True, if valid is not null and of the type returned by getType().
     */
    public default boolean isValid(Object value) {
        return value != null && getType().isInstance(value);
    }

    /**
     * Extracts and validates this enumerations value from a data store.
     * 
     * @param ds Data store that contains the value.
     * @return Validated value.
     * @throws IllegalArgumentException invalid, missing or null valid
     * @throws NullPointerException DataStore is null
     */
    public default boolean isValidValue(DataStore ds) {
        return isValid(get(ds));
    }

    /**
     * Uses isValid to confirm the validity of the value. If the value is
     * invalid IllegalArgumentException is thrown.
     * 
     * @param value Value validated by isValue
     * @throws IllegalArgumentException if isValid(value) return false.
     * @see #isValid(java.lang.Object) isValid(value)
     */
    public default void validate(Object value) {
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid value: " + value);
        }
    }
    
    /**
     * Retrieve and validate a value from the data store.
     * 
     * @param ds DataStore value is extracted from
     * @return Validated value from the data store.
     * @throws IllegalArgumentException invalid, missing or null valid
     * @throws NullPointerException DataStore is null
     */
    public default Object get(DataStore ds) {
        Object value = ds.get(name());
        validate(value);
        return value;
    }

    /**
     * Validate and put a value into the data store.
     * 
     * @param value Value to be validated and placed in the data store.
     * @param ds DataStore value is extracted from
     * @throws IllegalArgumentException invalid, missing or null valid
     * @throws NullPointerException DataStore is null
     */
    public default void put(Object value, DataStore ds) {
        validate(value);
        ds.put(name(), value);
    }

    /**
     * Enumeration name() method.
     * 
     * @return Name of an enumeration object. 
     */
    public String name();
}
