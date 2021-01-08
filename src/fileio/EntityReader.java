package fileio;

import entities.Consumer;
import entities.Distributor;
import entities.EntityFactory;
import entities.Producer;
import entities.EntityType;

/**
 * Class with static methods for creating entities with input data
 */
public final class EntityReader {
    private EntityReader() {
    }

    /**
     *
     * @param id the id
     * @param budget the initial budget
     * @param monthlyIncome the monthly income
     * @return an instance of a consumer
     */
    public static Consumer createNewConsumer(final int id, final long budget,
                                      final long monthlyIncome) {
        EntityFactory factory = new EntityFactory.Builder(id)
                .budget(budget)
                .monthlyIncome(monthlyIncome)
                .build();
        return (Consumer) factory.createEntity(EntityType.Consumer);
    }

    /**
     *
     * @param id the id
     * @param contractLength the contract length
     * @param budget the budget
     * @param infrastructureCost the infrastructure cost
     * @return an instance of a distributor
     */
    public static Distributor createNewDistributor(final int id, final int contractLength,
                                                   final long budget,
                                                   final long infrastructureCost,
                                                   final int energyNeeded,
                                                   final String strategyType) {
        EntityFactory factory = new EntityFactory.Builder(id)
                .contractLength(contractLength)
                .budget(budget)
                .infrastructureCost(infrastructureCost)
                .energyNeeded(energyNeeded)
                .strategyType(strategyType)
                .build();
        return (Distributor) factory.createEntity(EntityType.Distributor);
    }

    /**
     *
     * @param id the id
     * @param energyType the energy type
     * @param maxDistributors the max number of distributors
     * @param priceKW the price of a KW
     * @param energyPerDistributor the energy distributed
     * @return an instance of a producer
     */
    public static Producer createNewProducer(final int id, final String energyType,
                                             final int maxDistributors, final double priceKW,
                                             final int energyPerDistributor) {
        EntityFactory factory = new EntityFactory.Builder(id)
                                                    .energyType(energyType)
                                                    .maxDistributors(maxDistributors)
                                                    .priceKW(priceKW)
                                                    .energyPerDistributor(energyPerDistributor)
                                                    .build();
        return (Producer) factory.createEntity(EntityType.Producer);
    }
}
