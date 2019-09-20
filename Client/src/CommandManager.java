import java.io.BufferedReader;

/**
 * @author Thomas Beauchataud
 * @since 05.09.2019
 */
public class CommandManager implements CommandManagerInterface {

    /**
     * Return a Command created with the file in parameter
     * @param bufferedReader BufferedReader
     * @return Command
     */
    public Command creatCommand(BufferedReader bufferedReader) {
        Command command = new Command();
        String line;

        try {
            while ((line = bufferedReader.readLine()) != null) {
                command.addValue(line);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        return command;
    }

    /**
     * Return a the value of the command
     * @param command Command
     * @return String
     */
    public String explodeCommand(Command command) {
        String returnValue = "";
        for (String value : command.getValue()) {
            returnValue = returnValue + value + "\n";
        }

        return returnValue;
    }

}
