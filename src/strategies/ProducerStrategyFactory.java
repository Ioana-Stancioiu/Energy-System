package strategies;

import entities.Distributor;
import entities.Producer;

import java.util.List;

public class ProducerStrategyFactory {
    private static ProducerStrategyFactory instance = null;

    private ProducerStrategyFactory() {

    }

    public static ProducerStrategyFactory getInstance() {
        if (instance == null) {
            instance = new ProducerStrategyFactory();
        }
        return instance;
    }

    public ProducerStrategy createStrategy(final EnergyChoiceStrategyType strategyType,
                                           final List<Producer> producerList,
                                           final Distributor distributor) {
        switch(strategyType) {
            case GREEN -> {
                return new GreenStrategy(producerList, distributor);
            }
            case PRICE -> {
                return new PriceStrategy(producerList, distributor);
            }
            case QUANTITY -> {
                return new QuantityStrategy(producerList, distributor);
            }
        }
        throw new IllegalArgumentException("The producer strategy " + strategyType
                                                                    + " is not recognized.");
    }
}
