import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    // Fields

    // Getters and setters

    // Methods
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            // Instantiating the ServerSocket
            serverSocket = new ServerSocket(626);
            serverSocket.setReuseAddress(true);
            // Infinite while loop to get incoming clients
            while (true) {
                // Creating a new thread for the client to interact to the server with
                Socket clientSocket = serverSocket.accept();
                ClientHandler newPlayer = new ClientHandler(clientSocket);
                new Thread(newPlayer).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
