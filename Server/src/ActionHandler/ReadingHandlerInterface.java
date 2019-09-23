package ActionHandler;

public interface ReadingHandlerInterface {

    /**
     * Treat the reading of an attribute and return the result to send through the socket
     * @return String
     */
    String treat(String instanceName, String attribute);

}
