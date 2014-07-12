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

    public Object get(DataStore ds);

    public void put(Object value, DataStore ds);
}
