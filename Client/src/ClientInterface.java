/**
 * @author Thomas Beauchataud
 * @since 05.09.2019
 */
public interface ClientInterface {

    /**
     * Send a command to the server and return the command sent back from the server
     * @param command Command
     * @return Command
     */
    Command sendToServer(Command command);
}
