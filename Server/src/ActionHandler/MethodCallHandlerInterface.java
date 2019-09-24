package ActionHandler;

import java.util.List;

public interface MethodCallHandlerInterface {

	/**
     * Treat the call of a method with all its parameters and return the result to send through the socket
	 * @param objectName String
	 * @param methodName String
	 * @param parameters Object[]
     * @return String
     */
	String treat(String objectName, String methodName, Object[] parameters);

	/**
	 * Format parameters of the command and return a list of parameters
	 * Return null if there is not parameters
	 * It can use the PersistingHandler to find some saved elements
	 * @param objects List<Object>
	 * @return Object[]
	 */
	Object[] formatParameters(List<Object> objects);

}
