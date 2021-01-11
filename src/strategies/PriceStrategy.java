package strategies;

import entities.Distributor;
import entities.Producer;

import java.util.List;

/**
 * Class that implements price producer strategy
 */
public class PriceStrategy extends ProducerStrategy {
    public PriceStrategy(final List<Producer> producerList, final Distributor distributor) {
        super(producerList, distributor);
    }

    /**
     * Sorts list of producers by their price per KW and then by their quantity
     * of energy provided
     * @param producers list of producers
     */
    @Override
    public void sortProducerList(final List<Producer> producers) {
        producers.sort(((o1, o2) -> {
            if (Double.compare(o1.getPriceKW(), o2.getPriceKW()) == 0) {
                return o2.getEnergyPerDistributor() - o1.getEnergyPerDistributor();
            }
            return Double.compare(o1.getPriceKW(), o2.getPriceKW());
        }));
    }
}
