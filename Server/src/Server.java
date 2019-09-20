import java.io.*;
import java.net.*;

/**
 * @author Thomas Beauchataud & Francois Monitigny
 * @since 04.09.2019
 */
public class Server {

    /**
     * Run the server
     */
    private void run() {

        int port = 3004;
        ApplicationInterface application = new Application();

        try {

            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server started");

            while (true) {

                if (!serverSocket.isClosed()) {

                    System.out.println("Waiting for incoming connection");

                    Socket clientSocket = serverSocket.accept();

                    System.out.println("New connection accepted");

                    InputStream inputStream = clientSocket.getInputStream();
                    ObjectInput inputObject = new ObjectInputStream(inputStream);
                    Command command = (Command) inputObject.readObject();

                    System.out.println("Command created");

                    Command returnCommand = application.treatCommand(command);

                    System.out.println("Closing to connection");

                    clientSocket.close();
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Execute the server program
     * @param args String[]     */
    public static void main(String[] args) {
        Server server = new Server();
        server.run();
    }
}
