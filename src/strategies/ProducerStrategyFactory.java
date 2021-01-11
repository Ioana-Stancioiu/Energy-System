package strategies;

import entities.Distributor;
import entities.Producer;

import java.util.List;

/**
 * Singleton class that uses the factory design to return instances
 * of strategies based on their type
 */
public final class ProducerStrategyFactory {
    /**
     * class instance
     */
    private static ProducerStrategyFactory instance = null;

    private ProducerStrategyFactory() {
    }

    /**
     *
     * @return class instance
     */
    public static ProducerStrategyFactory getInstance() {
        if (instance == null) {
            instance = new ProducerStrategyFactory();
        }
        return instance;
    }

    /**
     *
     * @param strategyType the type of strategy
     * @param producerList list of producers
     * @param distributor distributor that applies strategy
     * @return instance of strategy
     */
    public ProducerStrategy createStrategy(final EnergyChoiceStrategyType strategyType,
                                           final List<Producer> producerList,
                                           final Distributor distributor) {
        switch (strategyType) {
            case GREEN -> {
                return new GreenStrategy(producerList, distributor);
            }
            case PRICE -> {
                return new PriceStrategy(producerList, distributor);
            }
            case QUANTITY -> {
                return new QuantityStrategy(producerList, distributor);
            }

            default -> { }
        }
        throw new IllegalArgumentException("The producer strategy " + strategyType
                                                                    + " is not recognized.");
    }
}
