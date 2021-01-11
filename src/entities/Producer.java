package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Class for a producer
 */
@JsonIgnoreProperties({"distributorList"})
@JsonPropertyOrder({"id", "maxDistributors", "priceKW", "energyType", "energyPerDistributor",
"monthlyStats"})
public class Producer extends Observable implements Entity {
    /**
     * producer's id
     */
    private final int id;

    /**
     * the type of energy provided
     */
    private final EnergyType energyType;

    /**
     * the maximum number of distributors per month
     */
    private final int maxDistributors;

    /**
     * the price per KW
     */
    private final double priceKW;

    /**
     * the energy provided per distributor
     */
    private int energyPerDistributor;

    /**
     * list of monthly stats
     */
    @JsonProperty("monthlyStats")
    private final List<MonthlyStats> monthlyDistributors;

    /**
     * list of distributors for current month
     */
    private final List<Distributor> distributorList;

    public Producer(final int id, final String energyType, final int maxDistributors,
                    final double priceKW, final int energyPerDistributor) {
        this.id = id;
        this.maxDistributors = maxDistributors;
        this.priceKW = priceKW;
        this.energyPerDistributor = energyPerDistributor;
        this.energyType = EnergyType.valueOf(energyType);
        this.monthlyDistributors = new ArrayList<>();
        this.distributorList = new ArrayList<>();
    }

    /**
     *
     * @return the id
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     *
     * @return energy type
     */
    public EnergyType getEnergyType() {
        return energyType;
    }

    /**
     *
     * @return maximum number of distributors
     */
    public int getMaxDistributors() {
        return maxDistributors;
    }

    /**
     *
     * @return price per KW
     */
    public double getPriceKW() {
        return priceKW;
    }

    /**
     *
     * @return list of monthly distributors
     */
    public List<MonthlyStats> getMonthlyDistributors() {
        return monthlyDistributors;
    }

    /**
     *
     * @return energy per distributor
     */
    public int getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    /**
     *
     * @return list of distributors per current month
     */
    public List<Distributor> getDistributorList() {
        return distributorList;
    }

    /**
     * Changes energy per distributor and notifies all distributors
     * @param newEnergyPerDistributor new energy per distributor
     */
    public void changeEnergyPerDistributor(final int newEnergyPerDistributor) {
        this.energyPerDistributor = newEnergyPerDistributor;
        setChanged();
        notifyObservers();
        clearChanged();
    }
}
