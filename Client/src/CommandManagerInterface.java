import java.io.BufferedReader;
import java.io.BufferedWriter;

/**
 * @author Thomas Beauchataud
 * @since 05.09.2019
 */
public interface CommandManagerInterface {

    /**
     * Return a Command created with the file in parameter
     * @param bufferedReader BufferedReader
     * @return Command
     */
    Command createCommand(BufferedReader bufferedReader);

    /**
     * Return a the value of the command
     * @param command Command
     * @return String
     */
    String explodeCommand(Command command);
}
