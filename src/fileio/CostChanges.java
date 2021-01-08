package fileio;

/**
 * Class for cost changes
 */
public class CostChanges {
    /**
     * id of the distributor that receives the changes
     */
    private final int id;

    /**
     * new infrastructure cost for distributor
     */
    private final long newInfrastructureCost;

    /**
     * new production cost for distributor
     */
    private final long newProductionCost;

    public CostChanges(final int id, final long newInfrastructureCost,
                       final long newProductionCost) {
        this.id = id;
        this.newInfrastructureCost = newInfrastructureCost;
        this.newProductionCost = newProductionCost;
    }

    /**
     *
     * @return the distributor's id
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return new infrastructure cost
     */
    public long getNewInfrastructureCost() {
        return newInfrastructureCost;
    }

    /**
     *
     * @return new production cost
     */
    public long getNewProductionCost() {
        return newProductionCost;
    }
}
