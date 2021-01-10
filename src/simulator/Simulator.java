package simulator;

import entities.Consumer;
import entities.Distributor;
import entities.Producer;
import fileio.DistributorChanges;
import fileio.Input;
import fileio.ProducerChanges;

import java.util.List;

/**
 * The class simulates the game
 */
public final class Simulator {
    /**
     * Variable containing input information
     */
    private final Input input;

    public Simulator(final Input input) {
        this.input = input;
    }

    /**
     * Begins simulation for current month
     */
    public void beginSimulation() {
        //calculates new contract price for all distributors
        int smallestRateDistributorId = DistributorUtils.
                                        computeContractCost(input.getDistributors());

        //checks if distributors have finished contracts and removes them
        DistributorUtils.checkForFinishedContracts(input.getDistributors());

        for (Consumer consumer : input.getConsumers()) {
            if (!consumer.isBankrupt()) {
                //add monthly income
                consumer.addIncome();

                //if consumer doesn't have a contract it chooses the distributor
                //with the lowest rate contract
                if (consumer.getCurrentDistributorId() == -1) {
                    Distributor distributor = input.getDistributors().
                                                get(smallestRateDistributorId);
                    DistributorUtils.addContract(distributor, consumer);
                }
            }
        }

        //distributors receive rates and pay costs
        DistributorUtils.computeDistributorBudget(input.getDistributors());
    }

    /**
     * Simulate game
     */
    public void simulate() {
        //distributors choose their producers and calculate their production cost
        DistributorUtils.chooseProducerStrategy(input.getDistributors(), input.getProducers());

        //Round 0
        beginSimulation();

        for (int i = 0; i < input.getNumberOfTurns(); i++) {
            List<Consumer> newConsumers = input.getMonthlyUpdates()
                                                            .get(i).getNewConsumers();
            List<DistributorChanges> distributorChanges = input.getMonthlyUpdates()
                                                            .get(i).getDistributorChanges();
            List<ProducerChanges> producerChanges = input.getMonthlyUpdates()
                                                            .get(i).getProducerChanges();

            //new consumers are added if they exist
            if (newConsumers != null) {
                input.getConsumers().addAll(newConsumers);
            }

            //distributor cost changes are made if they exist
            if (distributorChanges != null) {
                for (DistributorChanges costChange : distributorChanges) {
                    Distributor distributor = input.getDistributors().get(costChange.getId());
                    distributor.setInfrastructureCost(costChange.getNewInfrastructureCost());
                }
            }

            //start simulation
            beginSimulation();

            if (producerChanges != null) {
                for (ProducerChanges energyChanges : producerChanges) {
                    Producer producer = input.getProducers().get(energyChanges.getId());
                    producer.changeEnergyPerDistributor(energyChanges
                                                                .getNewEnergyPerDistributor());
                }
            }

            //check for bankrupt distributors
            DistributorUtils.checkForBankruptDistributors(input.getDistributors());
            //check for bankrupt consumers
            DistributorUtils.checkForBankruptConsumers(input.getDistributors());
            //producers remove their bankrupt distributors
            ProducerUtils.removeBankruptDistributors(input.getProducers());
            DistributorUtils.reapplyProducerStrategy(input.getDistributors());
            ProducerUtils.addDistributorsPerMonth(input.getProducers(), i+1);
        }
    }

}
