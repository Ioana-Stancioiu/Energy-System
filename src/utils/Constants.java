package utils;

/**
 * Contains constants for reading and writing
 */
public final class Constants {
    private Constants() {
    }
    //input and output constants
    public static final String NUMBER_OF_TURNS = "numberOfTurns";
    public static final String INITIAL_DATA = "initialData";
    public static final String CONSUMERS = "consumers";
    public static final String DISTRIBUTORS = "distributors";
    public static final String PRODUCERS = "producers";
    public static final String INITIAL_BUDGET = "initialBudget";
    public static final String MONTHLY_INCOME = "monthlyIncome";
    public static final String ID = "id";
    public static final String CONTRACT_LENGTH = "contractLength";
    public static final String INFRASTRUCTURE_COST = "initialInfrastructureCost";
    public static final String PRODUCTION_COST = "initialProductionCost";
    public static final String MONTHLY_UPDATES = "monthlyUpdates";
    public static final String NEW_CONSUMERS = "newConsumers";
    public static final String DISTRIBUTOR_CHANGES = "distributorChanges";
    public static final String PRODUCER_CHANGES = "producerChanges";
    public static final String NEW_PRODUCTION_COST = "productionCost";
    public static final String NEW_INFRASTRUCTURE_COST = "infrastructureCost";
    public static final String ENERGY_NEEDED = "energyNeededKW";
    public static final String PRODUCER_STRATEGY = "producerStrategy";
    public static final String ENERGY_TYPE = "energyType";
    public static final String MAX_DISTRIBUTORS = "maxDistributors";
    public static final String PRICE_KW = "priceKW";
    public static final String ENERGY_PER_DISTRIBUTOR = "energyPerDistributor";

    //formulas constants
    public static final double TWENTY_PERCENT = 0.2;
    public static final double PENALISATION_PERCENT = 1.2;
}
