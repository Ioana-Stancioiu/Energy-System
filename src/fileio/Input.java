package fileio;

import entities.Consumer;
import entities.Distributor;

import java.util.List;

/**
 * Class that stores input data
 */
public final class Input {
    /**
     * number of turns for simulation
     */
    private final int numberOfTurns;

    /**
     * list of consumers
     */
    private final List<Consumer> consumers;

    /**
     * list of distributors
     */
    private final List<Distributor> distributors;

    /**
     * list of monthly updates
     */
    private final List<MonthlyUpdate> monthlyUpdates;

    public Input(final int numberOfTurns, final List<Consumer> consumers,
                 final List<Distributor> distributors,
                 final List<MonthlyUpdate> monthlyUpdates) {

        this.numberOfTurns = numberOfTurns;
        this.consumers = consumers;
        this.distributors = distributors;
        this.monthlyUpdates = monthlyUpdates;
    }

    /**
     *
     * @return number of turns
     */
    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    /**
     *
     * @return list of consumers
     */
    public List<Consumer> getConsumers() {
        return consumers;
    }

    /**
     *
     * @return list of distributors
     */
    public List<Distributor> getDistributors() {
        return distributors;
    }

    /**
     *
     * @return list of monthly updates
     */
    public List<MonthlyUpdate> getMonthlyUpdates() {
        return monthlyUpdates;
    }
}
