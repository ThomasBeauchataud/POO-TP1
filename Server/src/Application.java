import ActionHandler.*;
import PersistingHandler.FilePersistingHandler;
import PersistingHandler.PersistingHandlerInterface;

import java.lang.Class;
import java.lang.Object;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Thomas Beauchataud & Francois Monitigny
 * @since 04.09.2019
 */
public class Application implements ApplicationInterface {

    private CompilationHandlerInterface compilationHandler;
    private CreationHandlerInterface creationHandler;
    private LoadingHandlerInterface loadingHandler;
    private ReadingHandlerInterface readingHandler;
    private WritingHandlerInterface writingHandler;
    private MethodCallHandlerInterface functionHandler;

    /**
     * Constructor Application
     */
    Application() {
        PersistingHandlerInterface persistingHandler = new FilePersistingHandler();
        compilationHandler = new CompilationHandler(persistingHandler);
        creationHandler = new CreationHandler(persistingHandler);
        loadingHandler = new LoadingHandler(persistingHandler);
        readingHandler = new ReadingHandler(persistingHandler);
        writingHandler = new WritingHandler(persistingHandler);
        functionHandler = new MethodCallHandler(persistingHandler);
    }

    /**
     * Treat a Command object
     *
     * @param command Command
     * @return Command
     */
    public Command treatCommand(Command command) {
        ArrayList<String> orders = command.getValue();
        Command returnCommand = new Command();
        log("Starting the loop for treatments");
        for (String order : orders) {
            Object[] orderSplit = order.split(command.getSeparator());
            try {
                String action = (String) orderSplit[0];
                if (action.equals("compilation")) {
                    String sourcePath = ((String) orderSplit[1]).replace(",", " ");
                    returnCommand.addValue(compilationHandler.treat(sourcePath));
                }
                if (action.equals("lecture")) {
                    returnCommand.addValue(readingHandler.treat((String) orderSplit[1], (String) orderSplit[2]));
                }
                if (action.equals("ecriture")) {
                    returnCommand.addValue(writingHandler.treat((String) orderSplit[1], (String) orderSplit[2], orderSplit[3]));
                }
                if (action.equals("creation")) {
                    returnCommand.addValue(creationHandler.treat(Class.forName((String) orderSplit[1]), (String) orderSplit[2]));
                }
                if (action.equals("chargement")) {
                    returnCommand.addValue(loadingHandler.treat((String) orderSplit[1]));
                }
                if(action.equals("fonction")) {
                    returnCommand.addValue(functionHandler.treat((String) orderSplit[1], (String)orderSplit[2], functionHandler.formatParameters(Arrays.asList(orderSplit))));
                }
            } catch (Exception e) {
                log(e.getMessage());
                returnCommand.addValue(e.getMessage());
            }
        }
        return returnCommand;
    }

    /**
     * Logging a message
     *
     * @param message String
     */
    private static void log(String message) {
        System.out.println(message);
    }

}
