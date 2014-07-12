/*
 * Copyright Heathman Innovative Solutions Jul 10, 2014.
 */

package helloworld;

import jade.Boot;

/**
 *
 * @author Brad
 */
public class HelloWorld {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Hello World");
        Boot.main(new String[] {
            "-container",
            "-agents",
            "HAL:helloworld.HelloAgent"
        });
    }
    
}
