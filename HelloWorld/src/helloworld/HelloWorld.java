/*
 * Copyright Heathman Innovative Solutions Jul 10, 2014.
 */
package helloworld;

import jade.Boot;
import jade.core.AID;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;
import jade.wrapper.StaleProxyException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Hello World application main for a JADE 'HelloWorld' container.
 *
 * <p>
 * Assumes a main container is running on the host.
 *
 * @author Brad
 */
public class HelloWorld {

    /**
     * Hello World boot arguments.
     */
    private static final String[] BOOT_ARGS = new String[]{
        "-container",
        "-container-name", "HelloWorld",
        "-agents",
        "HAL:helloworld.HelloAgent"
        + ";Dave:helloworld.HelloAgent"
    };
    /**
     * Hello World agent container created by main.
     */
    private static AgentContainer container;

    /**
     * Prints a simple hello world and starts the Hello World agent container.
     *
     * @param args the command line arguments (ignored)
     */
    public static void main(String[] args) {
        System.out.println("Hello World");
        getContainer(); // Called to start the container.
    }

    /**
     * Access the Hello World agent container.
     *
     * @return Hello World agent container (null if main not executed)
     */
    private static AgentContainer getContainer() {
        if (container == null) {
            final Profile p = new ProfileImpl(
                    Boot.parseCmdLineArgs(BOOT_ARGS));
            container = (AgentContainer) jade.core.Runtime.instance()
                    .createAgentContainer(p);
        }
        return container;
    }

    /**
     * Access the boot agent agent identifiers.
     *
     * @return List of boot agent identifiers.
     */
    public static List<String> bootAgentNames() {
        return Arrays.asList(((String) getBootValue(Profile.AGENTS)).split(";"))
                .stream()
                .map(a -> {
                    return a.split(":")[0];
                })
                .collect(Collectors.toList());
    }

    /**
     * Provide Hello World boot arguments as a list.
     *
     * @return List of Hello World boot arguments.
     */
    public static List bootArguments() {
        final List bootArgs = Arrays.asList(HelloWorld.BOOT_ARGS);
        return bootArgs;
    }

    /**
     * Return the value of a Hello World boot profile key.
     *
     * @param key JADE profile key
     * @return Value of HelloWorld boot arguments
     *
     * @see Profile
     */
    public static Object getBootValue(String key) {
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
     * @see Boot#parseCmdLineArgs(String[])
     * @see #BOOT_ARGS
     */
    public static Properties bootProperties() throws IllegalArgumentException {
        return Boot.parseCmdLineArgs(HelloWorld.BOOT_ARGS);
    }

    private HelloWorld() {
    }

}
