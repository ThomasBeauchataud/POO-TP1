package ActionHandler;

public interface MethodCallHandlerInterface {

	/**
     * Treat the call of a method with all its parameters and return the result to send through the socket
     * @return String
     */
	String treat(String objectName, String methodName, Object[] parameters);
}
