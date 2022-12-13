import java.io.Serializable;
import java.util.*;

public class Player implements Serializable {
    // Fields
    private String username;
    private ArrayList<Card> hand;

    // Constructors
    // Creating a brand-new player
    public Player(String username) {
        this.username = username;
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    // Methods

    /**
     * Adds a card to the player's hand.
     *
     * @param card card to be added.
     */
    public void addCardToHand(Card card) {
        hand.add(card);
    }

    /**
     * Plays the selected card to the game, removing it from the player's hand.
     *
     * @param card the card being played.
     */
    public Card playCard(Card card) {
        hand.remove(card);
        return card;
    }

    /**
     * Checks if the player hand is empty.
     *
     * @return true if their hand is empty, false if it isn't.
     */
    public boolean hasEmptyHand() {
        return hand.size() == 0;
    }

    /**
     * Checks if the player has one card left, meaning they need to say "Uno".
     *
     * @return true if they have one card in their hand, false if they don't.
     */
    public boolean hasUno() {
        return hand.size() == 1;
    }
}
