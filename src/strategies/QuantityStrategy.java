package strategies;

import entities.Distributor;
import entities.Producer;

import java.util.List;

public class QuantityStrategy extends ProducerStrategy {
    public QuantityStrategy(final List<Producer> producerList, final Distributor distributor) {
        super(producerList, distributor);
    }

    /**
     * Sorts list of producers by quantity of energy provided and then
     * by their order in the database
     * @param producers list of producers
     */
    @Override
    public void sortProducerList(final List<Producer> producers) {
        producers.sort(((o1, o2) -> o2.getEnergyPerDistributor()
                                        - o1.getEnergyPerDistributor()));
    }
}
