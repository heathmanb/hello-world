/*
 * Copyright Heathman Innovative Solutions Jul 11, 2014.
 */
package helloworld;

import jade.Boot;
import jade.core.AID;
import jade.core.Profile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
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
                .containsAll(bootProperties().keySet()));
    }

    @Test
    public void name_of_container() {
        assertEquals("name of container",
                getBootValue(Profile.CONTAINER_NAME),
                bootProperties().get(Profile.CONTAINER_NAME)
        );
    }

    @Test
    public void boot_agents_test() {
        assertEquals("agents list",
                getBootValue(Profile.AGENTS),
                bootProperties().get(Profile.AGENTS)
        );
    }
    
    @Test
    public void agents_started_test() {
        List<AID> names = bootAgentAIDs();
        System.out.println(names);
    }
    
    private static List<AID> bootAgentAIDs() {
        return
        Arrays.asList(((String) getBootValue(Profile.AGENTS)).split(";"))
                .stream()
                .map(a -> {
                   return a.split(":")[0];
                })
                .map(name -> {
                    return new AID(name,false);
                })
                .collect(Collectors.toList());
    }

    /**
     * Provide {@link HelloWorld#BOOT_ARGS} as a list.
     *
     * @return List of HelloWorld boot arguments.
     */
    private static List bootArguments() {
        final List bootArgs = Arrays.asList(HelloWorld.BOOT_ARGS);
        return bootArgs;
    }

    /**
     * Return the value of a {@link HelloWorld#BOOT_ARGS} profile key.
     *
     * @param key JADE profile key
     * @return Value of HelloWorld boot arguments
     *
     * @see Profile
     */
    private static Object getBootValue(String key) {
        final List bootArgs = bootArguments();
        final int keyPos = bootArgs.indexOf("-" + key);
        if (keyPos < 0) {
            throw new IllegalArgumentException("Key not found: " + key);
        }
        return bootArgs.get(keyPos + 1);
    }

    /**
     * Uses Boot.parseCmdLineArgs to parse the HelloWorld boot arguments.
     *
     * @return Properties parsed from HelloWorld boot arguments.
     * @throws IllegalArgumentException
     * @see Boot#parseCmdLineArgs(java.lang.String[])
     * @see HelloWorld#BOOT_ARGS
     */
    private static Properties bootProperties() throws IllegalArgumentException {
        return Boot.parseCmdLineArgs(HelloWorld.BOOT_ARGS);
    }

    /**
     * Shutdown JADE container.
     */
    @AfterClass
    public static void shut_down_JADE() {
        jade.core.Runtime.instance().shutDown();
    }
}
