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
public class Heartbeat extends TickerBehaviour {

    public Heartbeat(Agent agent, long timeout) {
        super(agent, timeout);
    }

    @Override
    public void  onTick() {
        myAgent.addBehaviour(new Hello(myAgent));
    }

}
