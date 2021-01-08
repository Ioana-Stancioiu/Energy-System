package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import utils.Constants;
import strategies.EnergyChoiceStrategyType;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for distributor
 */
@JsonIgnoreProperties({"contractLength", "infrastructureCost", "productionCost", "bankrupt",
                       "profit", "currentContractCost", "penalisedConsumers"})
@JsonPropertyOrder({"id", "budget", "isBankrupt", "contracts"})

public class Distributor implements Entity {
    /**
     * distributor's id
     */
    private final int id;

    /**
     * distributor's contract length
     */
    private final int contractLength;

    /**
     * distributor's budget
     */
    private long budget;

    /**
     * distributor's infrastructure cost
     */
    private long infrastructureCost;

    /**
     * distributor's production cost
     */
    private long productionCost;

    /**
     * distributor's profit
     */
    private long profit;

    /**
     * distributor's current contract cost
     */
    private long currentContractCost;

    /**
     * bankrupt status
     */
    @JsonProperty("isBankrupt")
    private boolean isBankrupt;

    /**
     * distributor's contracts
     */
    private final List<Contract> contracts;

    /**
     * distributor's consumers that have a penalisation to pay
     */
    private List<Contract> penalisedConsumers;

    private final int energyNeededKW;

    private final EnergyChoiceStrategyType strategyType;

    public Distributor(final int id, final int contractLength, final long budget,
                       final long infrastructureCost, final int energyNeededKW,
                       final String strategyType) {
        this.id = id;
        this.contractLength = contractLength;
        this.budget = budget;
        this.infrastructureCost = infrastructureCost;
        this.contracts = new ArrayList<>();
        this.isBankrupt = false;
        this.penalisedConsumers = null;
        this.energyNeededKW = energyNeededKW;
        this.strategyType = EnergyChoiceStrategyType.valueOf(strategyType);
    }

    /**
     *
     * @return id
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     *
     * @return budget
     */
    public long getBudget() {
        return budget;
    }

    /**
     *
     * @param budget the new budget
     */
    public void setBudget(final long budget) {
        this.budget = budget;
    }

    /**
     * Pay distributor's costs
     */
    public void payCosts() {
        this.budget = this.budget - infrastructureCost
                    - (productionCost * contracts.size());
    }

    /**
     * Adds income
     * @param income the rates accumulated for current month
     */
    public void addIncome(final long income) {
        this.budget += income;
    }

    /**
     *
     * @return contract length
     */
    public int getContractLength() {
        return contractLength;
    }

    /**
     *
     * @return infrastructure length
     */
    public long getInfrastructureCost() {
        return infrastructureCost;
    }

    /**
     *
     * @param infrastructureCost new infrastructure cost
     */
    public void setInfrastructureCost(final long infrastructureCost) {
        this.infrastructureCost = infrastructureCost;
    }

    /**
     *
     * @return production cost
     */
    public long getProductionCost() {
        return productionCost;
    }

    /**
     *
     * @param productionCost new production cost
     */
    public void setProductionCost(final long productionCost) {
        this.productionCost = productionCost;
    }

    /**
     *
     * @return profit
     */
    public long getProfit() {
        return profit;
    }

    /**
     * Calculates new profit
     */
    public void setProfit() {
        this.profit = Math.round(Math.floor(Constants.TWENTY_PERCENT * productionCost));
    }

    /**
     *
     * @return current contract cost
     */
    public long getCurrentContractCost() {
        return currentContractCost;
    }

    /**
     * Calculates new contract cost
     */
    public void setCurrentContractCost() {
        setProfit();
        if (this.contracts.size() == 0) {
            this.currentContractCost = infrastructureCost
                                        + productionCost
                                        + profit;
        } else {
            this.currentContractCost = Math.round(
                                        Math.floor(infrastructureCost / contracts.size())
                                                    + productionCost + profit);
        }
    }

    /**
     *
     * @return contracts
     */
    public List<Contract> getContracts() {
        return contracts;
    }

    /**
     *
     * @return true if bankrupt false otherwise
     */
    public boolean isBankrupt() {
        return isBankrupt;
    }

    /**
     * Change bankrupt status
     * @param bankrupt new bankrupt status
     */
    public void setBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    /**
     *
     * @return list of penalised consumers
     */
    public List<Contract> getPenalisedConsumers() {
        return penalisedConsumers;
    }

    /**
     *
     * @param penalisedConsumers new instance of penalised consumers
     */
    public void setPenalisedConsumers(final List<Contract> penalisedConsumers) {
        this.penalisedConsumers = penalisedConsumers;
    }

    /**
     *
     * @return energy needed in KW
     */
    public int getEnergyNeededKW() {
        return energyNeededKW;
    }

    /**
     *
     *
     * @return strategy type
     */
    public EnergyChoiceStrategyType getStrategyType() {
        return strategyType;
    }
}
