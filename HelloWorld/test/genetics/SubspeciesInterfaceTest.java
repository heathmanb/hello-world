/*
 * Copyright Heathman Innovative Solutions Jul 12, 2014.
 */
package genetics;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.TickerBehaviour;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * SubspeciesInterface tests.
 *
 * @author Brad
 */
public class SubspeciesInterfaceTest {

    /**
     * Test arguments: {"A", 1, 2L}
     */
    static final Object[] args = {"A", 1, 2L};
    /**
     * Test agent initialize before each test by
     * {@link #initAgent() initAgent()}.
     */
    private TestAgent testAgent;

    /**
     * Test implementation of SubspeciesInterface.
     */
    public static enum SI implements SubspeciesInterface {

        a,
        b;
        public static Behaviour[] testBehaviours;

        @Override
        public void initialize(Agent agent, Object... args) {
            switch (this) {
                case a:
                    agent.addBehaviour(testBehaviours[0]);
                case b:
                    agent.addBehaviour(testBehaviours[1]);
            }
        }
    }

    @Before
    public void before() {
        testAgent = new TestAgent();
        SI.testBehaviours = new Behaviour[]{
            new OneShotBehaviour(testAgent) {
                @Override
                public void action() {
                }
            },
            new TickerBehaviour(testAgent, 1000) {
                @Override
                protected void onTick() {
                }
            }
        };
    }

    /**
     * Test of initialize method, of class SubspeciesInterface.
     */
    @Test
    public void initialize_a_args() {
        SI.a.initialize(testAgent, args);
        assertArrayEquals(
                testAgent.behaviours.toArray(),
                SI.testBehaviours
        );
    }

    @Test
    public void initialize_b_args() {
        SI.b.initialize(testAgent, args);
        assertArrayEquals(
                testAgent.behaviours.toArray(),
                Arrays.copyOfRange(SI.testBehaviours, 1, 2)
        );
    }

    @Test
    public void initialize_a_nullargs() {
        final Object[] nullArgs = null;
        SI.a.initialize(testAgent, nullArgs);
        assertArrayEquals(
                testAgent.behaviours.toArray(),
                SI.testBehaviours
        );
    }
    @Test
    public void initialize_a_noargs() {
        SI.a.initialize(testAgent);
        assertArrayEquals(
                testAgent.behaviours.toArray(),
                SI.testBehaviours
        );
    }

    /**
     * Test of getArg method, of class SubspeciesInterface.
     */
    @Test
    public void getArg_valid_args() {
        final String def = "Test";
        assertEquals(args[0], SubspeciesInterface
                .getArg(0, def, args));
    }

    @Test
    public void getArg_invalid_arg() {
        final Long def = 1L;
        assertEquals(def, SubspeciesInterface
                .getArg(0, def, args));
    }

    @Test
    public void getArg_null_arg() {
        final Long def = 1L;
        final Object[] mArgs = Arrays.copyOf(args, 3);
        mArgs[0] = null;
        assertEquals(def, SubspeciesInterface
                .getArg(0, def, mArgs));
    }

    @Test
    public void getArg_null_args() {
        final Long def = 1L;
        final Object[] mArgs = null;
        assertEquals(def, SubspeciesInterface
                .getArg(0, def, mArgs));
    }

    @Test
    public void getArg_neg_index() {
        final Long def = 1L;
        assertEquals(def, SubspeciesInterface
                .getArg(-1, def, args));
    }

    @Test
    public void getArg_large_index() {
        final Long def = 1L;
        assertEquals(def, SubspeciesInterface
                .getArg(args.length + 1, def, args));
    }

    @Test
    public void getArg_default_null() {
        assertEquals(args[0], SubspeciesInterface
                .getArg(0, null, args));
    }

    @Test
    public void getArg_type_mismatch() {
        final Long def = 1L;
        assertEquals(def, SubspeciesInterface
                .getArg(0, def, args));
    }

    class TestAgent extends Agent {

        List<Behaviour> behaviours = new ArrayList();

        @Override
        public void addBehaviour(Behaviour b) {
            behaviours.add(b);
        }

    }

    public SubspeciesInterfaceTest() {
    }
}
