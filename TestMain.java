import java.io.*;
import java.net.*;

public class TestMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", 626);
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Player player = (Player) ois.readObject();
        System.out.println(player.getUsername());
    }
}
