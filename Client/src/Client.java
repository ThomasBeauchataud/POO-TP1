import java.io.*;
import java.net.Socket;

class Client implements ClientInterface{

    /**
     * @var ipAddress String
     */
    private String ipAddress;

    /**
     * @var port int
     */
    private int port;

    /**
     * Constructor Client
     * @param ipAddress String
     * @param port int
     */
    Client(String ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port = port;
    }

    /**
     * Send a command to the server and return the command sent back from the server
     * @param command Command
     * @return Command
     */
    public Command sendToServer(Command command) {
        try {
            Socket socket = new Socket(this.ipAddress, this.port);
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutput objectOutput = new ObjectOutputStream(outputStream);
            objectOutput.writeObject(command);
            objectOutput.flush();
            objectOutput.close();

            InputStream inputStream = socket.getInputStream();
            ObjectInput inputObject = new ObjectInputStream(inputStream);
            return (Command) inputObject.readObject();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        return null;
    }
}
