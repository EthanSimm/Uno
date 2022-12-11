import java.util.*;

public class Deck {
    // Fields
    private Card[] cards;

    // Constructors
    // Constructor to create a standard shuffled deck
    public Deck() {
        cards = createDeck();
        // Add shuffle method here
    }

    // Constructor to create a custom deck
    public Deck(Card[] cards) {
        this.cards = cards;
    }


    // Getters and setters

    // Methods
    private Card[] createDeck() {
        Card[] cards = new Card[108];
        int count = 0;

        // Creating all the cards
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 15; j++) {
                // Determines whether the card is a color card or a wild card
                if (j < 13) {
                    cards[count] = new Card(Color.getColor(i), Value.getValue(j));
                    // All cards get a duplicate except for the zeroes
                    if (j != 0) {
                        count++;
                        cards[count] = new Card(Color.getColor(i), Value.getValue(j));
                    }
                } else {
                    cards[count] = new Card(Color.WILD, Value.getValue(j));
                }
                count++;
            }
        }

        return cards;
    }
}
