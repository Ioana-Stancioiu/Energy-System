package entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Producer implements Entity{
    private final int id;
    private final EnergyType energyType;
    private final int maxDistributors;
    private final double priceKW;
    private int energyPerDistributor;
    private final Map<Integer, List<Distributor>> monthlyDistributors;

    public Producer(final int id, final String energyType, final int maxDistributors,
                    final double priceKW, final int energyPerDistributor) {
        this.id = id;
        this.maxDistributors = maxDistributors;
        this.priceKW = priceKW;
        this.energyPerDistributor = energyPerDistributor;
        this.energyType = EnergyType.valueOf(energyType);
        this.monthlyDistributors = new HashMap<>();
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

    public Map<Integer, List<Distributor>> getMonthlyDistributors() {
        return monthlyDistributors;
    }

    public int getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public void setEnergyPerDistributor(int energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }
}
