package fileio;

import entities.Consumer;
import entities.Distributor;
import entities.Producer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import utils.Constants;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that loads input information from input file
 */
public final class InputLoader {
    /**
     * the input file path
     */
    private final String inputPath;

    public InputLoader(final String inputPath) {
        this.inputPath = inputPath;
    }

    /**
     *
     * @return input file path
     */
    public String getInputPath() {
        return inputPath;
    }

    /**
     * Loads input information in instance of class Input
     * @return input an instance of class Input
     */
    public Input readData() throws IOException {
        int numberOfTurns = 0;
        JSONParser jsonParser = new JSONParser();
        List<Consumer> consumers = new ArrayList<>();
        List<Distributor> distributors = new ArrayList<>();
        List<Producer> producers = new ArrayList<>();
        List<MonthlyUpdate> monthlyUpdates = null;

        try {
            JSONObject jsonObject = (JSONObject) jsonParser
                    .parse(new FileReader(inputPath));

            numberOfTurns = Integer.parseInt(jsonObject.get(Constants.NUMBER_OF_TURNS).toString());
            JSONObject initialData = (JSONObject) jsonObject.get(Constants.INITIAL_DATA);
            JSONArray jsonConsumers = (JSONArray) initialData.get(Constants.CONSUMERS);
            JSONArray jsonDistributors = (JSONArray) initialData.get(Constants.DISTRIBUTORS);
            JSONArray jsonProducers = (JSONArray) initialData.get(Constants.PRODUCERS);

            if (jsonConsumers != null) {
               for (Object jsonConsumer : jsonConsumers) {
                   consumers.add(EntityReader.createNewConsumer(
                           Integer.parseInt(((JSONObject) jsonConsumer)
                                                        .get(Constants.ID).toString()),
                           (long) ((JSONObject) jsonConsumer)
                                                        .get(Constants.INITIAL_BUDGET),
                           (long) ((JSONObject) jsonConsumer)
                                                        .get(Constants.MONTHLY_INCOME)));
               }
            } else {
                System.out.println("CONSUMERS DO NOT EXIST");
            }

            if (jsonDistributors != null) {
                for (Object jsonDistributor : jsonDistributors) {
                    distributors.add(EntityReader.createNewDistributor(
                            Integer.parseInt(((JSONObject) jsonDistributor)
                                                        .get(Constants.ID).toString()),
                            Integer.parseInt(((JSONObject) jsonDistributor)
                                                        .get(Constants.CONTRACT_LENGTH)
                                                                            .toString()),
                            (long) ((JSONObject) jsonDistributor)
                                                        .get(Constants.INITIAL_BUDGET),
                            (long) ((JSONObject) jsonDistributor)
                                                        .get(Constants.INFRASTRUCTURE_COST),
                            Integer.parseInt(((JSONObject) jsonDistributor)
                                                        .get(Constants.ENERGY_NEEDED).toString()),
                            (String) ((JSONObject) jsonDistributor)
                                                        .get(Constants.PRODUCER_STRATEGY)));

                }
            } else {
                System.out.println("DISTRIBUTORS DO NOT EXIST");
            }

            if (jsonProducers != null) {
                for (Object jsonProducer : jsonProducers) {
                    producers.add(EntityReader.createNewProducer(
                            Integer.parseInt(((JSONObject) jsonProducer)
                                                        .get(Constants.ID).toString()),
                            (String) ((JSONObject) jsonProducer)
                                                        .get(Constants.ENERGY_TYPE),
                            Integer.parseInt(((JSONObject) jsonProducer)
                                                        .get(Constants.MAX_DISTRIBUTORS)
                                                        .toString()),
                            Double.parseDouble(((JSONObject) jsonProducer)
                                                        .get(Constants.PRICE_KW).toString()),
                            Integer.parseInt(((JSONObject) jsonProducer)
                                                        .get(Constants.ENERGY_PER_DISTRIBUTOR)
                                                        .toString())));
                }
            } else {
                System.out.println("PRODUCERS DO NOT EXIST");
            }

            monthlyUpdates = readMonthlyUpdates(jsonObject, numberOfTurns);

            if (jsonConsumers == null) {
                consumers = null;
            }

            if (jsonDistributors == null) {
                distributors = null;
            }

            if (jsonProducers == null) {
                producers = null;
            }

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        return new Input(numberOfTurns, consumers, distributors, producers, monthlyUpdates);
    }

    /**
     * Reads monthly updates
     * @param jsonObject a json object
     * @param size number of monthly updates
     * @return a list of monthly updates
     */
    public List<MonthlyUpdate> readMonthlyUpdates(final JSONObject jsonObject, final int size) {
        List<MonthlyUpdate> monthlyUpdates = new ArrayList<>();
        JSONArray jsonUpdates = (JSONArray) jsonObject.get(Constants.MONTHLY_UPDATES);

        if (jsonUpdates != null) {
            for (int i = 0; i < size; i++) {
                List<Consumer> consumers = null;
                List<DistributorChanges> distributorChanges = null;
                List<ProducerChanges> producerChanges = null;

                JSONObject jsonIterator = (JSONObject) jsonUpdates.get(i);
                JSONArray jsonNewConsumers = (JSONArray) jsonIterator
                                                        .get(Constants.NEW_CONSUMERS);
                JSONArray jsonDistributorChanges = (JSONArray) jsonIterator
                                                        .get(Constants.DISTRIBUTOR_CHANGES);
                JSONArray jsonProducerChanges = (JSONArray) jsonIterator
                                                        .get(Constants.PRODUCER_CHANGES);


                if (jsonNewConsumers != null) {
                    if (jsonNewConsumers.size() != 0) {
                        consumers = new ArrayList<>();
                        for (Object jsonConsumer : jsonNewConsumers) {
                            consumers.add(EntityReader.createNewConsumer(
                                    Integer.parseInt(((JSONObject) jsonConsumer)
                                            .get(Constants.ID).toString()),
                                    (long) ((JSONObject) jsonConsumer)
                                            .get(Constants.INITIAL_BUDGET),
                                    (long) ((JSONObject) jsonConsumer)
                                            .get(Constants.MONTHLY_INCOME)));
                        }

                    }
                }

                if (jsonDistributorChanges != null) {
                    if (jsonDistributorChanges.size() != 0) {
                        distributorChanges = new ArrayList<>();
                        for (Object jsonCostChange : jsonDistributorChanges) {
                            distributorChanges.add(new DistributorChanges(
                                            Integer.parseInt(((JSONObject) jsonCostChange)
                                                                .get(Constants.ID).toString()),
                                            (long) ((JSONObject) jsonCostChange)
                                                                .get(Constants.
                                                                        NEW_INFRASTRUCTURE_COST)));
                        }
                    }
                }

                if (jsonProducerChanges != null) {
                    if (jsonProducerChanges.size() != 0) {
                        producerChanges = new ArrayList<>();
                        for (Object jsonCostChange : jsonProducerChanges) {
                            producerChanges.add(new ProducerChanges(
                                    Integer.parseInt(((JSONObject) jsonCostChange)
                                            .get(Constants.ID).toString()),
                                    Integer.parseInt(((JSONObject) jsonCostChange)
                                            .get(Constants.ENERGY_PER_DISTRIBUTOR).toString())));
                        }
                    }
                }

                MonthlyUpdate update = new MonthlyUpdate(consumers, distributorChanges,
                                                         producerChanges);
                monthlyUpdates.add(update);
            }
        }
        return monthlyUpdates;
    }

}
