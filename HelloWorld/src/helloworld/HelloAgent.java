/*
 * Copyright Heathman Innovative Solutions Jul 11, 2014.
 */
package helloworld;

import genetics.SubspeciesInterface;
import genetics.DataKeyInterface;
import genetics.Species;
import helloworld.behaviours.Hello;
import helloworld.behaviours.Heartbeat;
import jade.core.Agent;
import jade.core.behaviours.DataStore;

/**
 * Basic Hello World agent. Prints 'Hello World from {name} setup.' when it
 * starts up.
 *
 * @author Brad
 */
public class HelloAgent extends Agent implements Species {

    /**
     * Hello agent subspecies.
     *
     * <ul>
     *
     * <li><b>Empty</b> No behaviours are added by the initializer.
     *
     * <li><b>Simple</b> A new instance of Hello is added by the initializer.
     *
     * <li><b>Heartbeat</b> A new empty one second Heartbeat is added by the
     * initializer.
     *
     * </ul>
     */
    public static enum Subspecies implements SubspeciesInterface {

        Empty,
        Simple,
        Heartbeat;

        @Override
        public void initialize(Agent agent) {
            switch (this) {
                case Heartbeat:
                    agent.addBehaviour(new Heartbeat(agent, 1000));
                    break;
                case Simple:
                    agent.addBehaviour(new Hello(agent));
                    break;
            }
        }
    }

    public static enum DataKey implements DataKeyInterface {

        HeartbeatBehaviour;

        @Override
        public Object get(DataStore ds) {
            return ds.get(this.name());
        }

        @Override
        public void put(Object value, DataStore ds) {
            ds.put(this.name(), value);
        }
    }
    
    private Subspecies subspecies = Subspecies.Empty;

    public HelloAgent() {
    }

    @Override
    protected void setup() {
        super.setup();
        // Startup hello message.
        System.out.println("Hello World from "
                + this.getLocalName()
                + " setup.");
        // Set the initial behaviours.
        subspecies.initialize(this);
    }

    @Override
    protected void takeDown() {
        super.takeDown();
    }

}
