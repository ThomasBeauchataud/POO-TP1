package ActionHandler;

import PersistingHandler.PersistingHandlerInterface;

import java.lang.reflect.Method;

public class ReadingHandler extends ActionHandler implements ReadingHandlerInterface {

	public ReadingHandler(PersistingHandlerInterface persistingHandler) {
		super(persistingHandler);
	}

	@Override
	public String treat(String instanceName, String attribute) {

		log("Treating a reading");
		attribute = this.upFirstLetter(attribute);
		Object object = persistingHandler.get(instanceName);
		try {
			Class<?> objectClass = Class.forName(object.getClass().getName());
			
			Method objectMethod = objectClass.getMethod("get" + attribute);
			return (String) objectMethod.invoke(object);
		} catch (Exception e) {
			log(e.getMessage());
			return "Impossible to read the attribute " + attribute + " of " + instanceName + " : " + e.getMessage();
		}
	}

	private String upFirstLetter(String string) {
		char[] attributeArray = string.toCharArray();
		attributeArray[0] = Character.toUpperCase(attributeArray[0]);
		return new String(attributeArray);
	}
}
