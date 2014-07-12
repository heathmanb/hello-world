/*
 * Copyright Heathman Innovative Solutions Jul 11, 2014.
 */

package helloworld.behaviours;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

/**
 *
 * @author Brad
 */
public class HelloTicker extends TickerBehaviour {

    public HelloTicker(Agent agent, long timeout) {
        super(agent, timeout);
    }

    @Override
    public void  onTick() {
        this.myAgent.addBehaviour(new Hello(myAgent));
    }

}