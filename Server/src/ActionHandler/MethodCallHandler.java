package ActionHandler;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import PersistingHandler.PersistingHandlerInterface;

public class MethodCallHandler extends ActionHandler implements MethodCallHandlerInterface {

	public MethodCallHandler(PersistingHandlerInterface persistingHandler) {
		super(persistingHandler);
	}

	@Override
	public String treat(String instanceName, String methodName, Object[] parameters) {
		log("Treating a method call");
		Object object = this.persistingHandler.get(instanceName);

		try {
			Class<?> objectClass = Class.forName(object.getClass().getName());

			Class[] parametersTypes = null;
			if(parameters != null) {
				parametersTypes = new Class[parameters.length];

				for (int i = 0; i < parameters.length; i++) {
					parametersTypes[i] = parameters[i].getClass();
				}
			}

			Method objectMethod = objectClass.getMethod(methodName, parametersTypes);
			Object result = objectMethod.invoke(object, parameters);

			this.persistingHandler.persist((Serializable) object, instanceName);

			return "Success when calling method " + methodName + " on instance " + instanceName + " with parameters "
					+ Arrays.toString(parameters) + "; result: " + result;
		} catch (Exception e) {
			log(e.getMessage());
			return "Impossible to call method " + methodName + " for " + instanceName + " : " + e.getMessage();
		}
	}

	@Override
	public Object[] formatParameters(List<Object> objects) {
		if (objects.size() <= 3) {
			return null;
		}
		String params = (String) objects.get(3);
		String[] paramsSplit = params.split(",");
		Object[] output = new Object[paramsSplit.length];
		for (int i = 0; i < paramsSplit.length; i++) {
			if (paramsSplit[i].indexOf(':') != -1) {
				String[] paramSplit = paramsSplit[i].split(":");
				if (paramSplit[0].equals("float")) {
					output[i] = Float.valueOf(paramSplit[1]);
				}
			}
			if (paramsSplit[i].indexOf('(') != -1 && paramsSplit[i].indexOf(')') != -1) {
				String name = paramsSplit[i].substring(paramsSplit[i].indexOf('(') + 1, paramsSplit[i].indexOf(')'));
				output[i] = this.persistingHandler.get(name);
			}
		}
		return output;
	}
}
