import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    // Fields
    private static HashMap<Integer, Game> ongoingGames;     // All ongoing games in the server, <Game ID, Game Object>
    private static ArrayList<Integer> takenGameIDs;


    // Static field declaration
    static {
        ongoingGames = new HashMap<>();
        takenGameIDs = new ArrayList<>();
    }

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

    public synchronized static void createNewGame() {
        int gameID = createNewGameID();
    }

    /**
     * Creates a unique game ID for a newly created game.
     *
     * @return the unique game ID for the ongoingGames HashMap.
     */
    private static int createNewGameID() {
        int id = 0;

        // Iterating through the taken ID's. They are always in ascending order
        for (int i : takenGameIDs) {
            // Looking to see if that ID is taken
            if (id == i) {
                id++;
            } else {
                // Adding the ID to the ArrayList while retaining ascending order
                takenGameIDs.add(id, id);
                return id;
            }
        }
        // Adding the ID to the end if there are not any open ID's in the ArrayList
        takenGameIDs.add(id);
        return id;
    }
}
