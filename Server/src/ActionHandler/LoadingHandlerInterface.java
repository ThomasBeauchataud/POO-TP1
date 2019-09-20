package ActionHandler;

public interface LoadingHandlerInterface {

    /**
     * Treat the loading of an attribute and return a confirmation to send threw the socket
     * @return String
     */
    String treat(String name);

}
