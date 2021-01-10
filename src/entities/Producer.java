package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.*;

@JsonIgnoreProperties({"distributorList"})
@JsonPropertyOrder({"id", "maxDistributors", "priceKW", "energyType", "energyPerDistributor",
"monthlyStats"})
public class Producer extends Observable implements Entity{
    private final int id;
    private final EnergyType energyType;
    private final int maxDistributors;
    private final double priceKW;
    private int energyPerDistributor;
    @JsonProperty("monthlyStats")
    private final List<MonthlyStats> monthlyDistributors;
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

    public int getId() {
        return id;
    }

    public EnergyType getEnergyType() {
        return energyType;
    }

    public int getMaxDistributors() {
        return maxDistributors;
    }

    public double getPriceKW() {
        return priceKW;
    }

    public List<MonthlyStats> getMonthlyDistributors() {
        return monthlyDistributors;
    }

    public int getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public List<Distributor> getDistributorList() {
        return distributorList;
    }

    public void changeEnergyPerDistributor(final int energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
       // this.distributorList.removeAll(new ArrayList<>(distributorList));
        setChanged();
        notifyObservers();
        clearChanged();
    }
}
