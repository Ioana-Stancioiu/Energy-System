package strategies;

import entities.Distributor;
import entities.Producer;

import java.util.List;

/**
 * Class that implements green producer strategy
 */
public class GreenStrategy extends ProducerStrategy {
    public GreenStrategy(final List<Producer> producerList, final Distributor distributor) {
        super(producerList, distributor);
    }

    /**
     * Sorts list of producers by renewable energy type provided, then
     * by their price per KW, and finally by the quantity of energy provided
     * @param producers list of producers
     */
    @Override
    public void sortProducerList(final List<Producer> producers) {
        //sort list by price and then by quantity
        producers.sort((o1, o2) -> {
            if (Double.compare(o1.getPriceKW(), o2.getPriceKW()) == 0) {
                return o2.getEnergyPerDistributor() - o1.getEnergyPerDistributor();
            }
            return Double.compare(o1.getPriceKW(), o2.getPriceKW());
        });

        //sort list by renewable energy types
        producers.sort((o1, o2) -> {
            if (o1.getEnergyType().isRenewable() && o2.getEnergyType().isRenewable()) {
                return 0;
            }
            return o1.getEnergyType().isRenewable() ? -1
                    : o2.getEnergyType().isRenewable() ? 1 : 0;
        });
    }

}
