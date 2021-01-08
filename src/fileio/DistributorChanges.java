package fileio;

/**
 * Class for distributor cost changes
 */
public class DistributorChanges {
    /**
     * id of the distributor that receives the changes
     */
    private final int id;

    /**
     * new infrastructure cost for distributor
     */
    private final long newInfrastructureCost;


    public DistributorChanges(final int id, final long newInfrastructureCost) {
        this.id = id;
        this.newInfrastructureCost = newInfrastructureCost;
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
}
