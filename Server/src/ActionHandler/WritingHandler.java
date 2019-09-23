package ActionHandler;

import PersistingHandler.PersistingHandlerInterface;

import java.lang.reflect.Method;

public class WritingHandler extends ActionHandler implements WritingHandlerInterface {

    public WritingHandler(PersistingHandlerInterface persistingHandler) {
        super(persistingHandler);
    }

    @Override
    public String treat(String instanceName, String attribute, Object value) {

        log("Treating a writing");
        Object object = this.persistingHandler.get(instanceName);
        Class objectClass = object.getClass();
        try {
            Method objectMethod = objectClass.getMethod(attribute);
            return (String)objectMethod.invoke(object, value);
        } catch (Exception e) {
            log(e.getMessage());
            return "Impossible to write the attribute " + attribute + " of " + instanceName + " : " + e.getMessage();
        }
    }

}
