/*
 * Copyright Heathman Innovative Solutions Jul 11, 2014.
 */
package helloworld.behaviours;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

/**
 *
 * @author Brad
 */
public class Hello extends OneShotBehaviour {

    public Hello(Agent agent) {
        super(agent);
    }

    @Override
    public void action() {
        //TODO: action for Hello.
        System.out.println(
                "Hello World from "
                + this.myAgent.getLocalName()
                + " behaviour."
        );
    }

}
