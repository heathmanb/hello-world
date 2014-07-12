/*
 * Copyright Heathman Innovative Solutions Jul 11, 2014.
 */
package helloworld;

import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import static org.junit.matchers.JUnitMatchers.*;

/**
 *
 * @author Brad
 */
public class HelloWorldTest {

    @Rule
    public final StandardOutputStreamLog log = new StandardOutputStreamLog();

    public HelloWorldTest() {
    }

    /**
     * Test of main method, of class HelloWorld.
     */
    @Test
    public void testMain() {
        HelloWorld.main(null);
        assertThat(log.getLog(), containsString("Hello World"));
    }

}
