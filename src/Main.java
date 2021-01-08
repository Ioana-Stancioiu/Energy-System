import fileio.Input;
import fileio.InputLoader;
import fileio.Writer;
import simulator.Simulator;

/**
 * The starting point for the game
 */
public final class Main {

    private Main() {

    }

    /**
     * Reads input data, simulates game and writes output data
     * @param args input and output files
     * @throws Exception if input and output files do not exist
     */
    public static void main(final String[] args) throws Exception {
        //args[0] -> input file
        //args[1] -> output file

        InputLoader inputLoader = new InputLoader(args[0]);
        Input input = inputLoader.readData();

        Simulator simulator = new Simulator(input);
        simulator.simulate();

        Writer fileWriter = new Writer(args[1]);
        fileWriter.writeFile(input);
    }
}
