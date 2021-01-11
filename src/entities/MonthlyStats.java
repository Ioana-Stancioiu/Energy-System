package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonIgnoreProperties({"distributorsIDs"})
@JsonPropertyOrder({"month", "distributorsIds"})
public class MonthlyStats {
    /**
     * current month
     */
    private final int month;

    /**
     * list of distributors' ids that received energy from producer
     */
    @JsonProperty("distributorsIds")
    private final List<Integer> distributorsIds;

    public MonthlyStats(final int month, final List<Integer> distributorsIds) {
        this.month = month;
        this.distributorsIds = distributorsIds;
    }

    /**
     *
     * @return month
     */
    public int getMonth() {
        return month;
    }

    /**
     *
     * @return list of distributors' ids
     */
    public List<Integer> getDistributorsIDs() {
        return distributorsIds;
    }
}
