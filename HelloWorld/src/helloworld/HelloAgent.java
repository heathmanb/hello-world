/*
 * Copyright Heathman Innovative Solutions Jul 11, 2014.
 */
package helloworld;

import jade.core.Agent;

/**
 *
 * @author Brad
 */
public class HelloAgent extends Agent {

    public HelloAgent() {
    }

    @Override
    protected void setup() {
        super.setup();
        System.out.println("Hello World from "
                + this.getLocalName()
                + " setup.");
    }

    @Override
    protected void takeDown() {
        super.takeDown();
    }

}
