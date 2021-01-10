package simulator;

import entities.Distributor;
import entities.MonthlyStats;
import entities.Producer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProducerUtils {
    private ProducerUtils() {
    }

    public static void addDistributorsPerMonth(final List<Producer> producers, final int month) {
        for (Producer producer : producers) {
            List<Integer> distributorIDs = new ArrayList<>();
            if (producer.getDistributorList().size() != 0) {
                for (Distributor distributor : producer.getDistributorList()) {
                    distributorIDs.add(distributor.getId());
                }
                distributorIDs.sort((Comparator.comparingInt(o -> o)));
            }
            producer.getMonthlyDistributors().add(new MonthlyStats(month, distributorIDs));
        }
    }

    public static void removeBankruptDistributors(final List<Producer> producers) {
        for (Producer producer : producers) {
            if (producer.getDistributorList().size() != 0) {
                for (Distributor distributor : producer.getDistributorList()) {
                    if (distributor.isBankrupt()) {
                        producer.getDistributorList().remove(distributor);
                        producer.deleteObserver(distributor);
                    }
                }
            }
        }
    }
}
