/*
 * Copyright Heathman Innovative Solutions Jul 11, 2014.
 */
package helloworld;

import jade.core.AID;
import jade.core.Profile;
import java.util.Arrays;
import java.util.List;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardErrorStreamLog;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import static org.junit.matchers.JUnitMatchers.*;

/**
 * HelloWorld integration tests.
 *
 * <p>
 * Assumes a main container is running on the host.
 *
 * @author Brad
 */
public class HelloWorldTest {

    @Rule
    public final StandardOutputStreamLog log = new StandardOutputStreamLog();
    @Rule
    public final StandardErrorStreamLog errLog = new StandardErrorStreamLog();

    /**
     * Standard out and err tests.
     */
    @Test
    public void main_test() {
        HelloWorld.main(null);
        assertThat("System.out test", log.getLog(),
                both(
                        containsString("Hello World"))
                .and(containsString("Hello World from HAL"))
                .and(containsString("Hello World from Dave"))
        );
        assertThat("System.err test", errLog.getLog(),
                containsString("This is JADE")
        );

    }

    @Test
    public void boot_arguments_keys_test() {
        assertTrue("Properties keys",
                Arrays.asList(new String[]{
                    Profile.CONTAINER_NAME,
                    Profile.MAIN,
                    Profile.AGENTS})
                .containsAll(HelloWorld.bootProperties().keySet()));
    }

    @Test
    public void name_of_container() {
        assertEquals("name of container",
                HelloWorld.getBootValue(Profile.CONTAINER_NAME),
                HelloWorld.bootProperties().get(Profile.CONTAINER_NAME)
        );
    }

    @Test
    public void boot_agents_test() {
        assertEquals("agents list",
                HelloWorld.getBootValue(Profile.AGENTS),
                HelloWorld.bootProperties().get(Profile.AGENTS)
        );
    }

    @Test
    public void agents_started_test() {
        List<AID> names = HelloWorld.bootAgentAIDs();
        System.out.println(names);
    }

    /**
     * Shutdown JADE container.
     */
    @AfterClass
    public static void shut_down_JADE() {
        jade.core.Runtime.instance().shutDown();
    }
}
