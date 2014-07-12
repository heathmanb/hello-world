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

    public default boolean isValid(Object value) {
        return getType().isInstance(value);
    }

    public default boolean isValid(DataStore ds) {
        return getType().isInstance(get(ds));
    }

    public default void validate(Object value) {
        if (!isValid(value)) {
            throw new IllegalArgumentException("Invalid value: " + value);
        }
    }

    public default Object get(DataStore ds) {
        return ds.get(name());
    }

    public default void put(Object value, DataStore ds) {
        validate(value);
        ds.put(name(), value);
    }

    public String name();
}
