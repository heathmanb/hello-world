/*
 * Copyright Heathman Innovative Solutions Jul 14, 2014.
 */

package helloworld.behaviours;

import helloworld.TestAgent;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;

/**
 *
 * @author Brad
 */
public class HeartbeatTest {
    
    @Rule
    public final StandardOutputStreamLog log = new StandardOutputStreamLog();

    public HeartbeatTest() {
    }

    /**
     * Test of onTick method, of class Heartbeat.
     */
    @Test
    public void testOnTick() {
        TestAgent ta = TestAgent.instance();
        Heartbeat instance = new Heartbeat(ta, 1000);
        instance.onTick();
        assertEquals(1, ta.behaviours.size());
        assertEquals(Hello.class, ta.behaviours.get(0).getClass());
    }
    
}
