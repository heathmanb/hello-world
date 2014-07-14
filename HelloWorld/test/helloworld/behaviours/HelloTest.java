/*
 * Copyright Heathman Innovative Solutions Jul 14, 2014.
 */

package helloworld.behaviours;

import jade.core.Agent;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import static org.junit.matchers.JUnitMatchers.containsString;

/**
 *
 * @author Brad
 */
public class HelloTest {
    
    public HelloTest() {
    }
    @Rule
    public final StandardOutputStreamLog log = new StandardOutputStreamLog();

    /**
     * Test of action method, of class Hello.
     */
    @Test
    public void testAction_null_agent() {
        log.clear();
        Hello hello = new Hello(null);
        hello.action();
        assertThat("System.out test", log.getLog(),
                containsString("Hello World from unnamed.")
        );
    }
    @Test
    public void testAction_agent() {
        log.clear();
        Hello hello = new Hello(new Agent());
        hello.action();
        assertThat("System.out test", log.getLog(),
                containsString("Hello World from null.")
        );
    }
    
}
