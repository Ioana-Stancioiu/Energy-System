package strategies;

import entities.Distributor;
import entities.Producer;

import java.util.ArrayList;
import java.util.List;

public abstract class ProducerStrategy {
    private final List<Producer> producerList;
    private final Distributor distributor;

    public ProducerStrategy(final List<Producer> producerList, final Distributor distributor) {
        this.producerList = producerList;
        this.distributor = distributor;
    }
    public abstract void sortProducerList(final List<Producer> producers);

    public long calculateProductionCost() {
        List<Producer> producers = new ArrayList<>(producerList);
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
        cost = Math.round(Math.floor(cost/10));
        return cost;
    }

}
