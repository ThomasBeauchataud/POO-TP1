package ActionHandler;

import PersistingHandler.PersistingHandlerInterface;

import java.io.Serializable;
import java.lang.reflect.Method;

public class WritingHandler extends ActionHandler implements WritingHandlerInterface {

	public WritingHandler(PersistingHandlerInterface persistingHandler) {
		super(persistingHandler);
	}

	@Override
	public String treat(String instanceName, String attribute, Object value) {

		log("Treating a writing");
		attribute = this.upFirstLetter(attribute);
		Object object = this.persistingHandler.get(instanceName);

		try {
			Class<?> objectClass = Class.forName(object.getClass().getName());

			// We're setting an attribute, so the length of the array can be one.
			Class[] parameters = new Class[1];
			parameters[0] = value.getClass();

			Method objectMethod = objectClass.getMethod("set" + attribute, parameters);
			objectMethod.invoke(object, value);

			this.persistingHandler.persist((Serializable) object, instanceName);

			return "Success when writing " + value + " in attribute " + attribute + " of instance " + instanceName;
		} catch (Exception e) {
			log(e.getMessage());
			return "Impossible to write the attribute " + attribute + " of " + instanceName + " : " + e.getMessage();
		}
	}

	private String upFirstLetter(String string) {
		char[] attributeArray = string.toCharArray();
		attributeArray[0] = Character.toUpperCase(attributeArray[0]);
		return new String(attributeArray);
	}

}
