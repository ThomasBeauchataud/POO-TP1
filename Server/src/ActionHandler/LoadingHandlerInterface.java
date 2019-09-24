package ActionHandler;

public interface LoadingHandlerInterface {

    /**
     * Treat the loading of an attribute and return a confirmation to send through the socket
     * @param name String, the file name to load
     * @return String
     */
    String treat(String name);

}
