package entities;

import utils.EntityType;

/**
 * A Singleton class that implements the factory design pattern for
 * Entity interface
 */
public final class EntityFactory {
    /**
     * instance of class
     */
    private static EntityFactory instance = null;

    private EntityFactory() {
    }

    /**
     * If instance doesn't exist create instance otherwise
     * return existing instance
     * @return the instance
     */
    public static EntityFactory getInstance() {
        if (instance == null) {
            instance = new EntityFactory();
        }
        return instance;
    }

    /**
     *
     * @param entityType an enum member specifying instance to return
     * @param id the id
     * @param contractLength the distributor's contract length
     * @param budget th budget
     * @param monthlyIncome the consumer's monthly income
     * @param infrastructureCost the distributor's infrastructure cost
     * @param productionCost the distributor's production cost
     * @return an instance of a class that implements entity interface
     */
    public Entity createEntity(final EntityType entityType, final int id,
                                        final int contractLength, final long budget,
                                        final long monthlyIncome, final long infrastructureCost,
                                        final long productionCost) {
        switch (entityType) {
            case Consumer: return new Consumer(id, budget, monthlyIncome);

            case Distributor: return new Distributor(id, contractLength, budget,
                                                     infrastructureCost, productionCost);
            default:
        }
        throw new IllegalArgumentException("The entity type " + entityType + " is not recognized.");
    }
}
