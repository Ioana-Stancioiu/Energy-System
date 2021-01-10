package strategies;

import entities.Distributor;
import entities.Producer;

import java.util.List;

public class PriceStrategy extends ProducerStrategy{
    public PriceStrategy(final List<Producer> producerList, final Distributor distributor) {
        super(producerList, distributor);
    }

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
