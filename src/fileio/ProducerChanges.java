package fileio;

/**
 * Class that contains producer cost changes
 */
public class ProducerChanges {
    /**
     * producer id
     */
    private final int id;

    /**
     * producer new energy per distributor
     */
    private final int newEnergyPerDistributor;

    public ProducerChanges(final int id, final int newEnergyPerDistributor) {
        this.id = id;
        this.newEnergyPerDistributor = newEnergyPerDistributor;
    }

    /**
     *
     * @return producer id
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return new energy provided per distributor
     */
    public int getNewEnergyPerDistributor() {
        return newEnergyPerDistributor;
    }
}
