import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TestMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InvalidGameSizeException, InvalidPlayException {
        Scanner sc = new Scanner(System.in);
        Player p1 = new Player("P1");
        Player p2 = new Player("P2");
        Player p3 = new Player("P3");
        Player p4 = new Player("P4");
        Player[] players = {p1, p2, p3, p4};
        Game game = new Game(players);


        while (game.gameOver() == null) {
            Player currentPlayer = game.getCurrentPlayer();
            int i = 0;
            for (Card c : currentPlayer.getHand()) {
                System.out.println(i + ") " + c);
                i++;
            }

            System.out.println("CURRENT CARD: " + game.getCenterCard());
            System.out.println("What card do you want to play?");
            int cardIndex = sc.nextInt();
            sc.nextLine();

            Card cardToPlay = currentPlayer.getHand().get(cardIndex);
            currentPlayer.playCard(cardToPlay);

            game.playCard(cardToPlay);
        }
    }
}
