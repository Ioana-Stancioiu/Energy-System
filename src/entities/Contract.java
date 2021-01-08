package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Class for contract
 */
@JsonPropertyOrder({"consumerId", "price", "remainedContractMonths"})
public class Contract {
    /**
     * the consumer that has contract
     */
    @JsonIgnore
    private final Consumer consumer;

    /**
     * id of consumer that has contract
     */
    private final int consumerId;

    /**
     * price of the contract
     */
    private final long price;

    /**
     * remaining months consumer has to pay contract
     */
    private int remainedContractMonths;

    public Contract(final Consumer consumer, final long price, final int remainedContractMonths) {
        this.consumer = consumer;
        this.price = price;
        this.remainedContractMonths = remainedContractMonths;
        this.consumerId = consumer.getId();
    }

    /**
     *
     * @return consumer
     */
    public Consumer getConsumer() {
        return consumer;
    }

    /**
     *
     * @return price
     */
    public long getPrice() {
        return price;
    }

    /**
     *
     * @return remaining contract months
     */
    public int getRemainedContractMonths() {
        return remainedContractMonths;
    }

    /**
     * Change remaining contract months
     * @param remainedContractMonths new remaining months
     */
    public void setRemainedContractMonths(final int remainedContractMonths) {
        this.remainedContractMonths = remainedContractMonths;
    }

    /**
     *
     * @return consumer's id
     */
    public int getConsumerId() {
        return consumerId;
    }
}
