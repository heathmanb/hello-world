/*
 * Copyright Heathman Innovative Solutions Jul 11, 2014.
 */
package helloworld;

import jade.Boot;
import jade.core.Profile;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Set;
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
    public void boot_arguments_test() {
        Properties params = bootProperties();
//        final Set keys = params.keySet();
//        System.out.println(keys);
        assertTrue("Properties keys", 
                Arrays.asList(new String[] {
        Profile.CONTAINER_NAME,
        Profile.MAIN,
        Profile.AGENTS})
                        .containsAll(bootProperties().keySet()));
        
        assertEquals("name of container",
                "HelloWorld",
                params.get(Profile.CONTAINER_NAME)
        );
        final List bootArgs = Arrays.asList(HelloWorld.BOOT_ARGS);
        final int agents_pos = bootArgs.indexOf("-" + Profile.AGENTS);
        final Object agents = params.get(Profile.AGENTS);
        System.out.println(params);
        assertEquals("agents list",
                bootArgs.get(agents_pos + 1),
                params.get(Profile.AGENTS)
        );
    }

    private Properties bootProperties() throws IllegalArgumentException {
        final Properties params = Boot.parseCmdLineArgs(HelloWorld.BOOT_ARGS);
        return params;
    }

    @AfterClass
    public static void shut_down_JADE() {
        jade.core.Runtime.instance().shutDown();
    }
}
