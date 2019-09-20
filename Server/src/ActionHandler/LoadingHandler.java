package ActionHandler;

import PersistingHandler.PersistingHandlerInterface;

public class LoadingHandler extends ActionHandler implements LoadingHandlerInterface {


    public LoadingHandler(PersistingHandlerInterface persistingHandler) {
        super(persistingHandler);
    }

    @Override
    public String treat(String name) {
        log("Treating a loading");
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        try {
            classLoader.loadClass(name);
            return "Success loading of " + name;
        } catch (Exception e) {
            log(e.getMessage());
            return "Impossible to load the class named " + name + " : " + e.getMessage();
        }
    }

}
