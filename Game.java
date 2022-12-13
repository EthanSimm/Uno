import java.util.*;

public class Game {
    // Fields
    private Player[] players;
    private Deck drawPile;
    private Card centerCard;
    private Player dealer;
    private LinkedList<Player> turnOrder;
    private TurnDirection turnOrderDirection;

    // Constructors


    public Game(Player[] players) {
        this.players = players;
        drawPile = new Deck();
        determineTurnOrder();
        centerCard = drawPile.drawCard();
        turnOrderDirection = TurnDirection.CLOCKWISE;
    }

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
     * Determine the turn order by calling the decideDealer method which determines the dealer as well.
     */
    public void determineTurnOrder() {
        int dealerIndex = decideDealer();

        // Pushing the players onto the LinkedList so that the turn order occurs in a clockwise fashion
        for (int i = dealerIndex; i < players.length; i++) {
            turnOrder.push(players[i]);
        }

        // Making sure the dealer isn't the first person in the array to prevent an out-of-bounds error
        if (dealerIndex == 0) return;
        for (int i = (dealerIndex - 1); i >= 0; i--) {
            turnOrder.push(players[i]);
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
     * Decides the dealer for the round by standard Uno convention.
     *
     * @return the array index of the dealer
     */
    private int decideDealer() {
        Card[] cardPerPlayer = new Card[players.length];
        Value highestValue = Value.ZERO;
        int highestCardIndex = -1;

        // Giving everyone a card. Whoever has the highest card value is the dealer
        for (int i = 0; i < cardPerPlayer.length; i++) {
            int cardIndexValue;
            // Drawing cards until the card has a number value
            do {
                // Drawing a card and immediately putting it back into the drawPile to maintain 108 cards
                cardPerPlayer[i] = drawPile.drawCard();
                drawPile.getCards().add(cardPerPlayer[i]);
                cardIndexValue = Value.getValueIndex(cardPerPlayer[i].getValue());
            } while (cardIndexValue > 9);

            // Comparing the value to the current highest value to see if it is higher
            if (cardIndexValue > Value.getValueIndex(highestValue)) {
                highestValue = cardPerPlayer[i].getValue();
                highestCardIndex = i;
            }
        }
        // Reshuffling the deck to knowing what cards are at the bottom and setting the dealer
        drawPile.shuffleDeck();
        dealer = players[highestCardIndex];
        return highestCardIndex;
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
