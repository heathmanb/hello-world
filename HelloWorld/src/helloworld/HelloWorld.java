/*
 * Copyright Heathman Innovative Solutions Jul 10, 2014.
 */
package helloworld;

import jade.Boot;

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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Hello World");
        Boot.main(BOOT_ARGS);
    }

}
