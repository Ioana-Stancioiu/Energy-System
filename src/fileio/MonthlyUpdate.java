package fileio;

import entities.Consumer;

import java.util.List;

/**
 * Class for monthly update
 */
public class MonthlyUpdate {
    /**
     * the list of new consumers
     */
    private final List<Consumer> newConsumers;

    /**
     * the list of cost changes
     */
    private final List<CostChanges> costChanges;

    public MonthlyUpdate(final List<Consumer> newConsumers, final List<CostChanges> costChanges) {
        this.newConsumers = newConsumers;
        this.costChanges = costChanges;
    }

    /**
     *
     * @return list of new consumers
     */
    public List<Consumer> getNewConsumers() {
        return newConsumers;
    }

    /**
     *
     * @return list of cost changes
     */
    public List<CostChanges> getCostChanges() {
        return costChanges;
    }
}
