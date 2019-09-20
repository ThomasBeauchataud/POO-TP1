/**
 * @author Thomas Beauchataud & Francois Monitigny
 * @since 04.09.2019
 */
public interface ApplicationInterface {

    /**
     * Treat a Command object
     * @return Command
     */
    Command treatCommand(Command command) throws ClassNotFoundException;

}
