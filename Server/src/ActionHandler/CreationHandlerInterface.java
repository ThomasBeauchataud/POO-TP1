package ActionHandler;

public interface CreationHandlerInterface {

    /**
     * Treat the creation of an attribute and return a confirmation to send through the socket
     * @param objectClass Class
     * @param identification String
     * @return String
     */
    String treat(Class objectClass, String identification);

}
