package ActionHandler;

import PersistingHandler.PersistingHandlerInterface;

public abstract class ActionHandler {

    PersistingHandlerInterface persistingHandler;

    public ActionHandler(PersistingHandlerInterface persistingHandler) {
        this.persistingHandler = persistingHandler;
    }

    /**
     * Logging a message
     * @param message String
     */
    static void log(String message) {
        System.out.println(message);
    }

}
