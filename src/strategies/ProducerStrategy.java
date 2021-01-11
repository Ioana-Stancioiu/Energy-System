package strategies;

import entities.Distributor;
import entities.Producer;
import utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for producer strategies
 */
public abstract class ProducerStrategy {
    /**
     * list of producers
     */
    private final List<Producer> producerList;

    /**
     * distributor that applies strategy
     */
    private final Distributor distributor;

    public ProducerStrategy(final List<Producer> producerList, final Distributor distributor) {
        this.producerList = producerList;
        this.distributor = distributor;
    }

    /**
     * Sorts producers by the criteria of each strategy type
     * @param producers list of producers
     */
    public abstract void sortProducerList(List<Producer> producers);

    /**
     * Calculates the distributor's production cost
     * @return production cost
     */
    public long calculateProductionCost() {
        List<Producer> producers = new ArrayList<>(producerList);

        //sort list of producers
        sortProducerList(producers);

        int energy = 0;
        long cost = 0;
        for (Producer producer : producers) {
            if (energy >= distributor.getEnergyNeededKW()) {
                break;
            }

            if (producer.getDistributorList().size() < producer.getMaxDistributors()) {
                distributor.getProducers().add(producer);
                producer.getDistributorList().add(distributor);
                producer.addObserver(distributor);

                energy += producer.getEnergyPerDistributor();
                cost += producer.getEnergyPerDistributor() * producer.getPriceKW();
            }
        }
        cost = Math.round(Math.floor(cost / Constants.TEN));

        return cost;
    }

}
