package entities;

/**
 * A class that implements the factory design pattern for
 * Entity interface
 */
public final class EntityFactory {
    /**
     * entity id
     */
    private final int id;

    /**
     * distributor contract length
     */
    private final int contractLength;

    /**
     * consumer/distributor initial budget
     */
    private final long budget;

    /**
     * consumer monthly income
     */
    private final long monthlyIncome;

    /**
     * distributor infrastructure cost
     */
    private final long infrastructureCost;

    /**
     * distributor needed energy
     */
    private final int energyNeeded;

    /**
     * distributor strategy type
     */
    private final String strategyType;

    /**
     * producer energy type
     */
    private final String energyType;

    /**
     * producer max number of distributors
     */
    private final int maxDistributors;

    /**
     * producer price per KW
     */
    private final double priceKW;

    /**
     * producer energy provided per distributor
     */
    private final int energyPerDistributor;

    /**
     * Builder class for implementing builder design pattern
     */
    public static class Builder {
        //id field is mandatory, all the other fields are optional

        private final int id;               //mandatory
        private int contractLength = 0;
        private long budget = 0;
        private long monthlyIncome = 0;
        private long infrastructureCost = 0;
        private int energyNeeded = 0;
        private String strategyType = "";
        private String energyType = "";
        private int maxDistributors = 0;
        private double priceKW = 0.00;
        private int energyPerDistributor = 0;

        public Builder(final int id) {
            this.id = id;
        }

        /**
         * Adds contract length to object
         * @param contractLen distributor contract length
         * @return builder object
         */
        public Builder contractLength(final int contractLen) {
            this.contractLength = contractLen;
            return this;
        }

        /**
         * Adds budget to object
         * @param initialBudget the budget
         * @return builder object
         */
        public Builder budget(final long initialBudget) {
            this.budget = initialBudget;
            return this;
        }

        /**
         * Adds monthly income to object
         * @param income consumer monthly income
         * @return builder object
         */
        public Builder monthlyIncome(final long income) {
            this.monthlyIncome = income;
            return this;
        }

        /**
         * Adds infrastructure cost to object
         * @param infraCost distributor infrastructure cost
         * @return builder object
         */
        public Builder infrastructureCost(final long infraCost) {
            this.infrastructureCost = infraCost;
            return this;
        }

        /**
         * Adds energy needed by distributor to object
         * @param energyNeededKW distributor needed energy
         * @return builder object
         */
        public Builder energyNeeded(final int energyNeededKW) {
            this.energyNeeded = energyNeededKW;
            return this;
        }

        /**
         * Adds strategy type to object
         * @param producerStrategy distributor strategy type
         * @return builder object
         */
        public Builder strategyType(final String producerStrategy) {
            this.strategyType = producerStrategy;
            return this;
        }

        /**
         * Adds energy type to object
         * @param enType producer energy type
         * @return builder object
         */
        public Builder energyType(final String enType) {
            this.energyType = enType;
            return this;
        }

        /**
         * Adds max number of distributors to object
         * @param maxNumDistributors producer max number of distributors
         * @return builder object
         */
        public Builder maxDistributors(final int maxNumDistributors) {
            this.maxDistributors = maxNumDistributors;
            return this;
        }

        /**
         * Adds price per KW to object
         * @param price producer price per KW
         * @return builder object
         */
        public Builder priceKW(final double price) {
            this.priceKW = price;
            return this;
        }

        /**
         * Adds energy per distributor to object
         * @param energyPerDistributorKW producer energy per distributor
         * @return builder object
         */
        public Builder energyPerDistributor(final int energyPerDistributorKW) {
            this.energyPerDistributor = energyPerDistributorKW;
            return this;
        }

        /**
         *
         * @return new instance of class EntityFactory
         */
        public EntityFactory build() {
            return new EntityFactory(this);
        }

    }

    private EntityFactory(Builder builder) {
        this.id = builder.id;
        this.budget = builder.budget;
        this.contractLength = builder.contractLength;
        this.monthlyIncome = builder.monthlyIncome;
        this.infrastructureCost = builder.infrastructureCost;
        this.energyNeeded = builder.energyNeeded;
        this.strategyType = builder.strategyType;
        this.energyType = builder.energyType;
        this.energyPerDistributor = builder.energyPerDistributor;
        this.maxDistributors = builder.maxDistributors;
        this.priceKW = builder.priceKW;
    }

    /**
     *
     * @param entityType an enum member specifying instance to return
     */
    public Entity createEntity(final EntityType entityType) {
        switch (entityType) {
            case Consumer: return new Consumer(id, budget, monthlyIncome);

            case Distributor: return new Distributor(id, contractLength, budget,
                                                     infrastructureCost, energyNeeded,
                                                    strategyType);

            case Producer: return new Producer(id, energyType, maxDistributors,
                                                priceKW, energyPerDistributor);
            default:
        }
        throw new IllegalArgumentException("The entity type " + entityType + " is not recognized.");
    }
}
