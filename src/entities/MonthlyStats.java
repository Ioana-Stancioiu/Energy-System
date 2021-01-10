package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonIgnoreProperties({"distributorsIDs"})
@JsonPropertyOrder({"month", "distributorsIds"})
public class MonthlyStats {
    private final int month;
    @JsonProperty("distributorsIds")
    private final List<Integer> distributorsIds;

    public MonthlyStats(final int month, final List<Integer> distributorsIds) {
        this.month = month;
        this.distributorsIds = distributorsIds;
    }

    public int getMonth() {
        return month;
    }

    public List<Integer> getDistributorsIDs() {
        return distributorsIds;
    }
}
