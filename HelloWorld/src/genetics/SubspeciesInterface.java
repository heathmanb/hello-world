/*
 * Copyright Heathman Innovative Solutions Jul 12, 2014.
 */
package genetics;

import jade.core.Agent;

/**
 * Interface for subspecies enumerations.
 *
 * @author Brad
 */
public interface SubspeciesInterface {

    public void initialize(Agent agent, Object... args);

    public default Object getArg(int index, Object defaultV, Object... args) {
        if (args == null                    // Arguments missing
                || index < 0                // Index to0 low
                || index > args.length - 1  // Index too high
                || args[index] == null      // Value is null
                || defaultV == null         // Default is null
                || !defaultV.getClass().isInstance(args[index]) ) {
            return defaultV;
        } else {
            return args[index];
        }
    }

}
