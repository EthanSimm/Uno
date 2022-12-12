import java.util.*;

public class Game {
    // Fields
    private Player[] players;
    private LinkedList<Player> turnOrder;
    private TurnDirection turnOrderDirection;
    private Deck drawPile;
    private Card centerCard;

    // Constructors

    // Getters and setters

    // Methods
    /**
     * Method that occurs when a player selects a card to play.
     * The card is checked to see if it is a valid play and makes it the center card.
     *
     * @param card card that is being played.
     * @throws InvalidPlayException when the card is not able to be played.
     */
    public void playCard(Card card) throws InvalidPlayException {
        if (isValidPlay(card)) {
            boolean skipped = false;
            centerCard = card;

            // Checking if the card is a special card **Need to insert code for changing a wild card to a color value**
            if (card.getValue() == Value.REVERSE) {
                turnOrderDirection = turnOrderDirection == TurnDirection.CLOCKWISE ?
                        TurnDirection.COUNTERCLOCKWISE : TurnDirection.CLOCKWISE;
            } else if (card.getValue() == Value.SKIP) {
                skipped = true;
            }

            advanceTurnOrder(skipped);
        } else {
            throw new InvalidPlayException();
        }
    }

    /**
     * Advances the player turn order.
     *
     * @param skipped Checks if the player before played a skip card.
     * @return the player whose turn it is after the advance.
     */
    public Player advanceTurnOrder(boolean skipped) {
        if (turnOrderDirection == TurnDirection.CLOCKWISE) {
            turnOrder.addLast(turnOrder.pop());
            // Advances twice if the player was skipped
            if (skipped) {
                turnOrder.addLast(turnOrder.pop());
            }
        } else {
            turnOrder.push(turnOrder.pollLast());
            // Advances twice if the player was skipped
            if (skipped) {
                turnOrder.push(turnOrder.pollLast());
            }
        }
        return turnOrder.peek();
    }

    /**
     * Looks to see if the card being played is a valid play.
     *
     * @param card the card being played.
     * @return true if it is valid, false if it is invalid.
     */
    private boolean isValidPlay(Card card) {
        return (card.getColor() == Color.WILD || centerCard.getColor() == card.getColor() ||
                centerCard.getValue() == card.getValue());
    }
}
