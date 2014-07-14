/*
 * Copyright Heathman Innovative Solutions Jul 12, 2014.
 */
package helloworld;

import helloworld.HelloAgent.DataKey;
import static helloworld.HelloAgent.DataKey.*;
import helloworld.HelloAgent.Subspecies;
import static helloworld.HelloAgent.Subspecies.*;
import static helloworld.TestAgent.*;
import helloworld.behaviours.Heartbeat;
import jade.core.behaviours.Behaviour;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import static org.junit.matchers.JUnitMatchers.containsString;

/**
 * Hello agent tests.
 *
 * @author Brad
 */
public class HelloAgentTest {

    public HelloAgentTest() {
    }

    /**
     * Subspecies test.
     */
    @Test
    public void testSubspecies_values() {
        assertArrayEquals(new Subspecies[]{Empty, Simple, Heartbeat},
                HelloAgent.Subspecies.values());
    }

    @Test
    public void testSubspecies_Empty_initialize() {
        insertTest(Empty);
    }

    @Test
    public void testSubspecies_Simple_initialize() {
        insertTest(Simple, helloworld.behaviours.Hello.class);
    }

    @Test
    public void testSubspecies_Heartbeat_initialize() {
        insertTest(Heartbeat, helloworld.behaviours.Heartbeat.class);
    }

    /**
     * Test the initializer of subspecies adds behaviours of the specified
     * classes in the specified order.
     *
     * @param subspecies Subspecies being tested.
     * @param types Expected behaviour types and order.
     */
    private void insertTest(
            HelloAgent.Subspecies subspecies,
            Class<? extends Behaviour>... types) {
        final TestAgent ta = new TestAgent();
        subspecies.initialize(ta);
        if (types.length != ta.behaviours.size()) {
            fail();
        } else {
            int i = 0;
            for (Behaviour b : ta.behaviours) {
                if (!types[i++].isInstance(b)) {
                    break;
                };
            }
            assertEquals(types.length, i);
        }
    }
    
    /**
     * Test the DataKeys.
     */
    @Test
    public void testDataKey_values() {
        assertArrayEquals(new DataKey[]{HeartRateMs, HeartbeatBehaviour},
                HelloAgent.DataKey.values());
    }
    @Test
    public void testDataKey_getType_HeartRateMs() {
        assertEquals(Long.class, HeartRateMs.getType());
    }
    @Test
    public void testDataKey_getType_HeartbeatBehaviour() {
        assertEquals(Behaviour.class, HeartbeatBehaviour.getType());
    }
    
    /**
     * Test setup.
     */
    @Rule
    public final StandardOutputStreamLog log = new StandardOutputStreamLog();
    
    @Test
    public void testSetup_behaviours() {
        TestAgent ta = new TestAgent();
        ta.setup();
        assertEquals(0, ta.behaviours.size());
    }
    @Test
    public void testSetup_output() {
        log.clear();
        TestAgent ta = new TestAgent();
        ta.setup();
        assertThat("System.out test", log.getLog(),
                containsString("Hello World from null setup.")
        );
    }

}
