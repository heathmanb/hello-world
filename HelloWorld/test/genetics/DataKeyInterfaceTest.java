/*
 * Copyright Heathman Innovative Solutions Jul 12, 2014.
 */

package genetics;

import jade.core.behaviours.DataStore;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Brad
 */


public class DataKeyInterfaceTest {

    public DataKeyInterfaceTest() {
    }

    /**
     * Test of getType method, of class DataKeyInterface.
     */
    @Test
    public void testGetType_a() {
        assertEquals(Long.class, DK.a.getType());
    }
    @Test
    public void testGetType_b() {
        assertEquals(String.class, DK.b.getType());
    }

    /**
     * Test of isValid method, of class DataKeyInterface.
     */
    @Test
    public void testIsValid_a_valid() {
        assertTrue(DK.a.isValid(1L));
    }
    @Test
    public void testIsValid_a_invalid() {
        assertFalse(DK.a.isValid(""));
    }
    @Test
    public void testIsValid_a_null() {
        assertFalse(DK.a.isValid(null));
    }

    /**
     * Test of isValidValue method, of class DataKeyInterface.
     */
    @Test(expected = NullPointerException.class)
    public void testIsValidValue_null_ds() {
        DK.a.isValidValue(null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testIsValidValue_missing_pair() {
        DK.a.isValidValue(new DataStore());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testIsValidValue_null_value() {
        DataStore ds = new DataStore();
        ds.put(DK.a.name(), null);
        assertFalse(DK.a.isValidValue(ds));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testIsValidValue_invalid_value() {
        DataStore ds = new DataStore();
        ds.put(DK.a.name(), "");
        DK.a.isValidValue(ds);
    }
    @Test
    public void testIsValidValue_valid_value() {
        DataStore ds = new DataStore();
        ds.put(DK.a.name(), 2L);
        assertTrue(DK.a.isValidValue(ds));
    }

    /**
     * Test of validate method, of class DataKeyInterface.
     */
    @Test
    public void testValidate_a_valid() {
        DK.a.validate(1L);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testValidate_a_invalid() {
        DK.a.validate("");
    }
    @Test(expected = IllegalArgumentException.class)
    public void testValidate_a_null() {
        DK.a.validate(null);
    }

    /**
     * Test of get method, of class DataKeyInterface.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGet_a_missing() {
        DK.a.get(new DataStore());
    }
    @Test(expected = NullPointerException.class)
    public void testGet_a_ds_null() {
        DK.a.get(null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testGet_a_null() {
        DataStore ds = new DataStore();
        ds.put(DK.a.name(), null);
        DK.a.get(ds);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testGet_a_invalid() {
        DataStore ds = new DataStore();
        ds.put(DK.a.name(), "");
        DK.a.get(ds);
    }
    @Test
    public void testGet_a_valid() {
        DataStore ds = new DataStore();
        long expected = 2;
        ds.put(DK.a.name(), expected);
        assertEquals(expected, DK.a.get(ds));
    }

    /**
     * Test of put method, of class DataKeyInterface.
     */
    @Test(expected = NullPointerException.class)
    public void testPut_a_ds_null() {
        DK.a.put(1L, null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testPut_a_null() {
        DK.a.put(null, new DataStore());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testPut_a_invalid() {
        DK.a.put("", new DataStore());
    }
    @Test
    public void testPut_a_valid() {
        DK.a.put(2L, new DataStore());
        assertTrue(true);
    }

    /**
     * Test of name method, of class DataKeyInterface.
     */
    @Test
    public void testName_a() {
        assertEquals(DK.a.name(), "a");
    }
    @Test
    public void testName_b() {
        assertEquals(DK.b.name(), "b");
    }

    public enum DK implements DataKeyInterface {

        a, b;

        @Override
        public Class<?> getType() {
            switch (this) {
                case a:
                    return Long.class;
                default:
                    return String.class;
            }
        }
    }

}
