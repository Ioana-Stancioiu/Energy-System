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
     * the list of distributor cost changes
     */
    private final List<DistributorChanges> distributorChanges;

    /**
     * the list of producer cost changes
     */
    private final List<ProducerChanges> producerChanges;

    public MonthlyUpdate(final List<Consumer> newConsumers,
                         final List<DistributorChanges> distributorChanges,
                         final List<ProducerChanges> producerChanges) {
        this.newConsumers = newConsumers;
        this.distributorChanges = distributorChanges;
        this.producerChanges = producerChanges;
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
     * @return list of distributor cost changes
     */
    public List<DistributorChanges> getDistributorChanges() {
        return distributorChanges;
    }

    /**
     *
     * @return list of producer cost changes
     */
    public List<ProducerChanges> getProducerChanges() {
        return producerChanges;
    }
}
