import java.util.*;

public class Deck {
    // Fields
    private ArrayList<Card> cards;

    // Constructors
    /**
     * Constructor to create a standard shuffled deck.
     */
    public Deck() {
        cards = createDeck();
        shuffleDeck();
    }

    /**
     * Constructor to create an empty or custom deck.
     * @param cards cards that are going to start in the deck.
     */
    public Deck(ArrayList<Card> cards) {
        this.cards = cards;
    }


    // Getters and setters

    // Methods
    /**
     * Draws the top card from the deck and returns it, effectively removing the card from the deck.
     * @return the top card from the deck.
     */
    public Card drawCard() {
        Card temp = cards.get(0);
        cards.remove(0);
        return temp;
    }

    /**
     * Shuffles the deck, sorting the cards in a random order.
     */
    public void shuffleDeck() {
        int index;
        // Picks a random card and places it at the bottom of the deck, 1296 times (108 * 12)
        for (int i = 0; i < 1296; i++) {
            index = (int) (Math.random() * cards.size());
            cards.add(cards.get(index));
            cards.remove(index);
        }
    }

    /**
     * Creates a brand-new Uno deck with all 108 cards.
     * @return a new full Uno deck.
     */
    private ArrayList<Card> createDeck() {
        ArrayList<Card> cards = new ArrayList<>(108);

        // Creating all the cards
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 15; j++) {
                // Determines whether the card is a color card or a wild card
                if (j < 13) {
                    cards.add(new Card(Color.getColor(i), Value.getValue(j)));
                    // All cards get a duplicate except for the zeroes
                    if (j != 0) {
                        cards.add(new Card(Color.getColor(i), Value.getValue(j)));
                    }
                } else {
                    cards.add(new Card(Color.WILD, Value.getValue(j)));
                }
            }
        }

        return cards;
    }
}
