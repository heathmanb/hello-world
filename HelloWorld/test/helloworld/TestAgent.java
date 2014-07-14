/*
 * Copyright Heathman Innovative Solutions Jul 14, 2014.
 */

package helloworld;

import jade.core.behaviours.Behaviour;
import java.util.ArrayList;
import java.util.List;

/**
 * Agent that extends HelloAgent to short out and provide test access to
 * behaviours added by initialize.
 */
public class TestAgent extends HelloAgent {
    public List<Behaviour> behaviours = new ArrayList();

    TestAgent() {
    }

    public static TestAgent instance() {
        final TestAgent ta = new TestAgent();
        return ta;
    }

    @Override
    public void addBehaviour(Behaviour b) {
        behaviours.add(b);
    }
    
}
