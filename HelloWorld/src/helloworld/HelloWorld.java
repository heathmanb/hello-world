/*
 * Copyright Heathman Innovative Solutions Jul 10, 2014.
 */
package helloworld;

import jade.Boot;
import jade.wrapper.AgentContainer;
import jade.core.Profile;
import jade.core.ProfileImpl;

/**
 * Hello World application main for a JADE 'HelloWorld' container.
 *
 * <p>
 * Assumes a main container is running on the host.
 *
 * @author Brad
 */
public class HelloWorld {

    public static final String[] BOOT_ARGS = new String[]{
        "-container",
        "-container-name", "HelloWorld",
        "-agents",
        "HAL:helloworld.HelloAgent"
            +";Dave:helloworld.HelloAgent"
    };
    private static AgentContainer container;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Hello World");
        final Profile p = new ProfileImpl(
                Boot.parseCmdLineArgs(BOOT_ARGS));
        container = (AgentContainer) jade.core.Runtime.instance()
                .createAgentContainer(p);
//        Boot.main(BOOT_ARGS);
    }

}
