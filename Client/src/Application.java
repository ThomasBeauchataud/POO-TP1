import PersistingHandler.FilePersistingHandler;
import PersistingHandler.PersistingHandlerInterface;

import java.io.*;

/**
 * @author Thomas Beauchataud
 * @since 05.09.2019
 */
class Application {

    private ClientInterface client;
    private CommandManagerInterface commandManager;
    private PersistingHandlerInterface persistingHandler;

    /**
     * Constructor Application
     */
    private Application() {
        System.out.println("Program started");
        this.client = new Client("localhost", 3004);
        this.commandManager = new CommandManager();
        this.persistingHandler = new FilePersistingHandler();
    }

    /**
     * Open files to read input file and to write output file
     * @param inputFile String
     * @param outputFile String
     */
    private void initialize(String inputFile, String outputFile) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
            Command command = this.commandManager.createCommand(bufferedReader);
            bufferedReader.close();

            Command returnCommand = this.treatCommand(command);
            
            String returnValue = this.commandManager.explodeCommand(returnCommand);

            persistingHandler.persist(returnValue, outputFile);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Given test method.
     *
     * @param inputFile the path to the commands input file.
     */
    public void scenario(String inputFile) {
        System.out.println("Beginning treatments:");
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(inputFile));
            Command next = this.commandManager.createCommand(bufferedReader);
            while (next != null) {
                System.out.println("\tTreating command " + next + " ...");
                Object result = this.treatCommand(next);
                System.out.println("\t\tResult: " + result);
                next = this.commandManager.createCommand(bufferedReader);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        System.out.println("End of treatments.");
    }

    /**
     * Send a Command to the server and return the result sent back from the server
     * If there is no result, return null
     * @param command Command
     * @return Command|null
     */
    private Command treatCommand(Command command) {
        return this.client.sendToServer(command);
    }

    /**
     * Execution the program
     * @param args String[]
     */
    public static void main(String[] args) {
        Application application = new Application();
        application.initialize("ressources/commandes.txt", "../ressources/result.txt");
    }

}
