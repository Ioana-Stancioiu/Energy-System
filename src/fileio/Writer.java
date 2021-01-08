package fileio;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.node.ObjectNode;
import utils.Constants;

import java.io.File;

import java.io.IOException;

/**
 * Class that writes output file
 */
public final class Writer {
    /**
     * Output file
     */
    private final File file;

    public Writer(final String path) {
        this.file = new File(path);
    }

    /**
     * Writes output info in file
     * @param input contains the information to be written
     */
    public void writeFile(final Input input) {

        try {

            ObjectMapper mapper = new ObjectMapper();
            ObjectNode objectNode = mapper.createObjectNode();

            objectNode.putPOJO(Constants.CONSUMERS, input.getConsumers());
            objectNode.putPOJO(Constants.DISTRIBUTORS, input.getDistributors());

            mapper.writerWithDefaultPrettyPrinter().writeValue(file, objectNode);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
