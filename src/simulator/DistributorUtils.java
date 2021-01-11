package simulator;

import entities.Consumer;
import entities.Contract;
import entities.Distributor;
import entities.Producer;
import strategies.ProducerStrategyFactory;
import utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Class contains static methods for a list of distributors
 * and their contracts and clients
 */
public final class DistributorUtils {

    private DistributorUtils() {

    }

    /**
     * Calculates the distributors' new contract prices
     * @param distributors a list of distributors
     * @return id of the distributor with the smallest rate
     */
    public static int computeContractCost(final List<Distributor> distributors) {
        long smallestRate = Long.MAX_VALUE;
        int distributorId = -1;

        for (Distributor distributor : distributors) {
            if (!distributor.isBankrupt()) {
                distributor.setCurrentContractCost();

                if (smallestRate > distributor.getCurrentContractCost()) {
                    smallestRate = distributor.getCurrentContractCost();
                    distributorId = distributor.getId();
                }
            }
        }
        return distributorId;
    }

    /**
     * Creates a new contract and adds it to distributor's contract list
     * @param distributor the distributor
     * @param consumer the consumer
     */
    public static void addContract(final Distributor distributor, final Consumer consumer) {
        distributor.getContracts().add(new Contract(consumer,
                                                    distributor.getCurrentContractCost(),
                                                    distributor.getContractLength()));

        consumer.setCurrentDistributorId(distributor.getId());
    }

    /**
     * Resolves the distributor's consumers which have debts
     * @param distributor the distributor
     * @return the accumulated rates that get added to the distributor's budget
     */
    public static long getPenalisedConsumersRates(final Distributor distributor) {
        long income = 0;

        if (distributor.getPenalisedConsumers() != null) {
            List<Contract> toBeRemoved = new ArrayList<>();
            for (Contract contract : distributor.getPenalisedConsumers()) {
                if (!contract.getConsumer().isBankrupt()) {
                    //check if consumer can pay penalisation or not
                    if (contract.getConsumer().payPenalisation(contract.getPrice())) {
                        income += Math.round(Math.floor(Constants.PENALISATION_PERCENT
                                * contract.getPrice()))
                                + contract.getPrice();

                        contract.getConsumer().setBehindWithPayments(false);
                        toBeRemoved.add(contract);

                    } else {
                        contract.getConsumer().setBankrupt(true);
                    }

                } else {
                    toBeRemoved.add(contract);
                }
            }
            //remove bankrupt consumers
            distributor.getPenalisedConsumers().removeAll(toBeRemoved);
        }
        return income;
    }

    /**
     * Calculates the distributor's income
     * @param distributor the distributor
     * @return the income(the rates from his clients)
     */
    public static long getDistributorIncome(final Distributor distributor) {
        long income = 0;

        //gets the rates from the consumers that are behind with payments
        income += getPenalisedConsumersRates(distributor);

        //gets consumers' rates
        for (Contract contract : distributor.getContracts()) {
            if (!contract.getConsumer().isBankrupt()) {
                if (!contract.getConsumer().isBehindWithPayments()) {
                    //consumer can pay rate or not
                    if (contract.getConsumer().getBudget() >= contract.getPrice()) {
                        contract.getConsumer().setBudget(contract.getConsumer().getBudget()
                                                        - contract.getPrice());

                        income += contract.getPrice();
                    } else {
                        if (distributor.getPenalisedConsumers() == null) {
                            distributor.setPenalisedConsumers(new ArrayList<>());
                        }

                        //add consumer to penalised consumers list
                        if (!distributor.getPenalisedConsumers().contains(contract)) {
                            contract.getConsumer().setBehindWithPayments(true);
                            distributor.getPenalisedConsumers().add(contract);
                        }
                    }
                }
            }
            //decrease remaining contract months
            contract.setRemainedContractMonths(contract.getRemainedContractMonths() - 1);
        }

        return income;
    }

    /**
     * Calculates distributor new budget after receiving rates and paying costs
     * @param distributors the list of distributors
     */
    public static void computeDistributorBudget(final List<Distributor> distributors) {
        for (Distributor distributor : distributors) {
            if (!distributor.isBankrupt()) {
                distributor.addIncome(getDistributorIncome(distributor));
                distributor.payCosts();
            }
        }
    }

    /**
     * Finds bankrupt consumers' contracts
     * @param contracts the list of contracts
     * @return a list of contracts to be removed
     */
    public static List<Contract> findBankruptContracts(final List<Contract> contracts) {
        List<Contract> found = new ArrayList<>();
        for (Contract contract : contracts) {
            if (contract.getConsumer().isBankrupt()) {
                found.add(contract);
            }
        }
        return found;
    }

    /**
     * Removes bankrupt consumers' contracts
     * @param distributor the distributor
     */
    public static void removeBankruptConsumers(final Distributor distributor) {
        distributor.getContracts().removeAll(findBankruptContracts((distributor.
                                                            getContracts())));
    }

    /**
     * Checks for bankrupt consumers and removes them if found
     * @param distributors the list of distributors
     */
    public static void checkForBankruptConsumers(final List<Distributor> distributors) {
        for (Distributor distributor : distributors) {
            removeBankruptConsumers(distributor);
        }
    }

    /**
     * Checks for bankrupt distributors
     * @param distributors a list of distributors
     */
    public static void checkForBankruptDistributors(final List<Distributor> distributors) {
        for (Distributor distributor : distributors) {
            //if budget lower that zero distributor is bankrupt
            if (distributor.getBudget() < 0) {
                //remove his contracts and erase consumers' debts
                for (Contract contract : distributor.getContracts()) {
                    contract.getConsumer().setCurrentDistributorId(-1);
                    contract.getConsumer().setBehindWithPayments(false);
                }
                distributor.getContracts().removeAll(distributor.getContracts());
                if (distributor.getPenalisedConsumers() != null) {
                    distributor.getPenalisedConsumers().removeAll(distributor.
                                                                getPenalisedConsumers());
                }
                distributor.setBankrupt(true);
            }
        }
    }

    /**
     * Check for contracts that have remaining months 0 and removes them
     * @param distributors a list of distributors
     */
    public static void checkForFinishedContracts(final List<Distributor> distributors) {
        List<Contract> found = new ArrayList<>();

        for (Distributor distributor : distributors) {
            for (Contract contract : distributor.getContracts()) {
                if (contract.getRemainedContractMonths() == 0) {
                    contract.getConsumer().setCurrentDistributorId(-1);
                    found.add(contract);
                }
            }
            distributor.getContracts().removeAll(found);
        }

    }

    /**
     * Distributors apply their strategy to choose producers and calculate
     * their production cost
     * @param distributors list of distributors
     * @param producers list of producers
     */
    public static void chooseProducerStrategy(final List<Distributor> distributors,
                                              final List<Producer> producers) {
        ProducerStrategyFactory strategyFactory = ProducerStrategyFactory.getInstance();

        for (Distributor distributor : distributors) {
            distributor.setChosenProducerStrategy(strategyFactory.createStrategy(
                                                            distributor.getStrategyType(),
                                                            producers,
                                                            distributor));
            distributor.setProductionCost(distributor
                                            .getChosenProducerStrategy()
                                            .calculateProductionCost());
        }
    }

    /**
     * Reapplies producer strategy for every distributors that has to
     * @param distributors list of distributors
     */
    public static void reapplyProducerStrategy(final List<Distributor> distributors) {
        for (Distributor distributor : distributors) {
            if (distributor.hasToReapplyProducerStrategy() && !distributor.isBankrupt()) {
                //remove distributor from his producers' lists
                for (Producer producer : distributor.getProducers()) {
                    producer.getDistributorList().remove(distributor);
                    producer.deleteObserver(distributor);
                }
                //calculate production cost
                distributor.setProductionCost(distributor
                        .getChosenProducerStrategy().calculateProductionCost());
                distributor.setReapplyProducerStrategy(false);
            }
        }
    }

}
