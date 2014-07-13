/*
 * Copyright Heathman Innovative Solutions Jul 11, 2014.
 */
package helloworld;

import genetics.DataKeyInterface;
import genetics.Species;
import genetics.SubspeciesInterface;
import helloworld.behaviours.Heartbeat;
import helloworld.behaviours.Hello;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import static genetics.SubspeciesInterface.getArg;

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
        public void initialize(Agent agent, Object... args) {
            switch (this) {
                case Heartbeat:
                    agent.addBehaviour(
                            new Heartbeat(agent,
                                     (Long) getArg(0, 1000L, args))
                    );
                    break;
                case Simple:
                    agent.addBehaviour(new Hello(agent));
                    break;
            }
        }

    }

    public static enum DataKey implements DataKeyInterface {
        
        HeartRateMs(Long.class),
        HeartbeatBehaviour(Behaviour.class);

        private final Class<?> type;

        private DataKey(Class<?> type) {
            this.type = type;
        }

        @Override
        public Class<?> getType() {
            return type;
        }
    }

    @SuppressWarnings("FieldMayBeFinal")
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
