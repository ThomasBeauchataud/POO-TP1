package ActionHandler;

public interface WritingHandlerInterface {

    /**
     * Treat the writing of an attribute and return a confirmation to send threw the socket
     * @return String
     */
    String treat(String instanceName, String attribute, Object value);

}
