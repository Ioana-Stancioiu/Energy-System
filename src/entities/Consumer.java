package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import utils.Constants;

/**
 * Class for a consumer
 */

@JsonIgnoreProperties({"monthlyIncome", "currentDistributorId", "behindWithPayments", "bankrupt"})
@JsonPropertyOrder({"id", "isBankrupt", "budget"})
public class Consumer implements Entity {
    /**
     * consumer's id
     */
    private final int id;


    /**
     * consumer's bankrupt status
     */
    @JsonProperty("isBankrupt")
    private boolean isBankrupt;

    /**
     * consumer's budget
     */
    private long budget;

    /**
     * consumer's monthly income
     */
    private final long monthlyIncome;

    /**
     * the id of the consumer's current distributor
     */
    private int currentDistributorId;

    /**
     * true if consumer is behind with payments
     */
    private boolean isBehindWithPayments;

    public Consumer(final int id, final long budget, final long monthlyIncome) {
        this.id = id;
        this.budget = budget;
        this.monthlyIncome = monthlyIncome;
        this.isBankrupt = false;
        this.currentDistributorId = -1;
        this.isBehindWithPayments = false;
    }


    /**
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
    @Override
    public long getBudget() {
        return budget;
    }

    /**
     *
     * @param budget the new budget
     */
    @Override
    public void setBudget(final long budget) {
        this.budget = budget;
    }

    /**
     * Adds monthly income to budget
     */
    public void addIncome() {
        this.budget += this.monthlyIncome;
    }

    /**
     *
     * @return monthly income
     */
    public long getMonthlyIncome() {
        return monthlyIncome;
    }

    /**
     *
     * @return bankrupt status
     */
    @Override
    public boolean isBankrupt() {
        return isBankrupt;
    }

    /**
     *
     * @param bankrupt the new bankrupt status
     */
    @Override
    public void setBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    /**
     *
     * @return current distributor's id
     */
    public int getCurrentDistributorId() {
        return currentDistributorId;
    }

    /**
     * Sets current distributor's id
     * @param currentDistributorId the current distributor's id
     */
    public void setCurrentDistributorId(final int currentDistributorId) {
        this.currentDistributorId = currentDistributorId;
    }

    /**
     *
     * @return true is behind with payments false otherwise
     */
    public boolean isBehindWithPayments() {
        return isBehindWithPayments;
    }

    /**
     * Set behind with payments status
     * @param behindWithPayments the new status
     */
    public void setBehindWithPayments(final boolean behindWithPayments) {
        isBehindWithPayments = behindWithPayments;
    }

    /**
     * Pay penalisation if behind with payments
     * @param currentBill the current rate to be payed
     * @return true if successfully payed false if it consumer can't pay
     */
    public boolean payPenalisation(final long currentBill) {
        long bill = Math.round(Math.floor(Constants.PENALISATION_PERCENT * currentBill))
                    + currentBill;
        if (bill <= budget) {
            this.budget -= bill;
            return true;
        }
        return false;
    }

}
