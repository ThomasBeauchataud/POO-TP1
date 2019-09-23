package ActionHandler;

import java.lang.reflect.Method;
import java.util.Arrays;

import PersistingHandler.PersistingHandlerInterface;

public class MethodCallHandler extends ActionHandler implements MethodCallHandlerInterface {

	public MethodCallHandler(PersistingHandlerInterface persistingHandler) {
		super(persistingHandler);
	}

	@Override
	public String treat(String instanceName, String methodName, Object[] parameters) {
		log("Treating a method call");
		Object object = this.persistingHandler.get(instanceName);
		Class objectClass = object.getClass();

		Class[] parametersTypes = new Class[parameters.length];

		for (int i = 0; i < parameters.length; i++) {
			parametersTypes[i] = parameters[i].getClass();
		}

		try {
			Method objectMethod = objectClass.getMethod(methodName, parametersTypes);
			return "Success when calling method " + methodName + " on instance " + instanceName + " with parameters "
					+ Arrays.toString(parameters);
		} catch (Exception e) {
			log(e.getMessage());
			return "Impossible to call method " + methodName + " for " + instanceName + " : " + e.getMessage();
		}
	}

}
