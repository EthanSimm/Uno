import java.net.*;

public class ClientHandler implements Runnable {
    // Fields
    private Socket socket;
    private Player player;

    // Constructors
    public ClientHandler(Socket socket) {
        this.socket = socket;
        player = null;
    }

    // Methods
    @Override
    public void run() {

    }
}
