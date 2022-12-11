import java.util.*;

public class Game {
    // Fields
    private Deque<Player> turnOrder;    // when instantiating -> = new ArrayDeque<>();
    private Deck drawPile;
    private Card centerCard;

    // Constructors


    // Getters and setters

    // Methods
    /**
     * Looks to see if the card being played is a valid play.
     * @param card the card being played.
     * @return true if it is valid, false if it is invalid.
     */
    public boolean isValidPlay(Card card) {
        return (card.getColor() == Color.WILD || centerCard.getColor() == card.getColor() ||
                centerCard.getValue() == card.getValue());
    }
}
