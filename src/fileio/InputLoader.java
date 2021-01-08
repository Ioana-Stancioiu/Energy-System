package fileio;

import entities.Consumer;
import entities.Distributor;
import entities.EntityFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import utils.Constants;
import utils.EntityType;

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
        EntityFactory factory = EntityFactory.getInstance();
        JSONParser jsonParser = new JSONParser();
        List<Consumer> consumers = new ArrayList<>();
        List<Distributor> distributors = new ArrayList<>();
        List<MonthlyUpdate> monthlyUpdates = null;

        try {
            JSONObject jsonObject = (JSONObject) jsonParser
                    .parse(new FileReader(inputPath));

            numberOfTurns = Integer.parseInt(jsonObject.get(Constants.NUMBER_OF_TURNS).toString());
            JSONObject initialData = (JSONObject) jsonObject.get(Constants.INITIAL_DATA);
            JSONArray jsonConsumers = (JSONArray) initialData.get(Constants.CONSUMERS);
            JSONArray jsonDistributors = (JSONArray) initialData.get(Constants.DISTRIBUTORS);

            if (jsonConsumers != null) {
               for (Object jsonConsumer : jsonConsumers) {
                   consumers.add((Consumer) factory.createEntity(EntityType.Consumer,
                           Integer.parseInt(((JSONObject) jsonConsumer)
                                                .get(Constants.ID).toString()),
                           0,
                           (long) ((JSONObject) jsonConsumer).get(Constants.INITIAL_BUDGET),
                           (long) ((JSONObject) jsonConsumer).get(Constants.MONTHLY_INCOME),
                           0, 0));
               }
            } else {
                System.out.println("CONSUMERS DO NOT EXIST");
            }

            if (jsonDistributors != null) {
                for (Object jsonDistributor : jsonDistributors) {
                    distributors.add((Distributor) factory.createEntity(EntityType.Distributor,
                            Integer.parseInt(((JSONObject) jsonDistributor)
                                                .get(Constants.ID).toString()),
                            Integer.parseInt(((JSONObject) jsonDistributor)
                                                .get(Constants.CONTRACT_LENGTH).toString()),
                            (long) ((JSONObject) jsonDistributor)
                                                .get(Constants.INITIAL_BUDGET),
                            0,
                            (long) ((JSONObject) jsonDistributor)
                                                .get(Constants.INFRASTRUCTURE_COST),
                            (long) ((JSONObject) jsonDistributor)
                                                .get(Constants.PRODUCTION_COST)));
                }
            } else {
                System.out.println("DISTRIBUTORS DO NOT EXIST");
            }

            monthlyUpdates = readMonthlyUpdates(jsonObject, numberOfTurns, factory);

            if (jsonConsumers == null) {
                consumers = null;
            }

            if (jsonDistributors == null) {
                distributors = null;
            }

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        return new Input(numberOfTurns, consumers, distributors, monthlyUpdates);
    }

    /**
     * Reads monthly updates
     * @param jsonObject a json object
     * @param size number of monthly updates
     * @param factory an instance of EntityFactory
     * @return a list of monthly updates
     */
    public List<MonthlyUpdate> readMonthlyUpdates(final JSONObject jsonObject, final int size,
                                                  final EntityFactory factory) {
        List<MonthlyUpdate> monthlyUpdates = new ArrayList<>();
        JSONArray jsonUpdates = (JSONArray) jsonObject.get(Constants.MONTHLY_UPDATES);

        if (jsonUpdates != null) {
            for (int i = 0; i < size; i++) {
                List<Consumer> consumers = null;
                List<CostChanges> costChanges = null;

                JSONObject jsonIterator = (JSONObject) jsonUpdates.get(i);
                JSONArray jsonNewConsumers = (JSONArray) jsonIterator.get(Constants.NEW_CONSUMERS);
                JSONArray jsonCostChanges = (JSONArray) jsonIterator.get(Constants.COSTS_CHANGES);

                if (jsonNewConsumers != null) {
                    if (jsonNewConsumers.size() != 0) {
                        consumers = new ArrayList<>();
                        for (Object jsonConsumer : jsonNewConsumers) {
                            consumers.add((Consumer) factory.createEntity(
                                        EntityType.Consumer,
                                        Integer.parseInt(((JSONObject) jsonConsumer)
                                                            .get(Constants.ID).toString()),
                                        0,
                                        (long) ((JSONObject) jsonConsumer)
                                                            .get(Constants.INITIAL_BUDGET),
                                        (long) ((JSONObject) jsonConsumer)
                                                            .get(Constants.MONTHLY_INCOME),
                                        0, 0));
                        }

                    }
                }

                if (jsonCostChanges != null) {
                    if (jsonCostChanges.size() != 0) {
                        costChanges = new ArrayList<>();
                        for (Object jsonCostChange : jsonCostChanges) {
                            costChanges.add(new CostChanges(
                                            Integer.parseInt(((JSONObject) jsonCostChange)
                                                                .get(Constants.ID).toString()),
                                            (long) ((JSONObject) jsonCostChange)
                                                                .get(Constants.
                                                                        NEW_INFRASTRUCTURE_COST),
                                            (long) ((JSONObject) jsonCostChange)
                                                                .get(Constants.
                                                                        NEW_PRODUCTION_COST)));
                        }
                    }
                }

                MonthlyUpdate update = new MonthlyUpdate(consumers, costChanges);
                monthlyUpdates.add(update);
            }
        }
        return monthlyUpdates;
    }

}
