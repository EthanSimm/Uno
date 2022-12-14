import java.util.*;

public class Game {
    // Fields
    private ArrayList<Player> players;
    private Deck drawPile;
    private Card centerCard;
    private Player dealer;
    private LinkedList<Player> turnOrder;
    private TurnDirection turnOrderDirection;
    private GameStatus status;

    // Constructors

    // ** Need to rewrite constructor so that it creates a game instance but doesn't start anything till
    // Someone decides to beginning the game with the players currently in it. Changing the game status to ONGOING.

    /*public Game(Player[] players) throws InvalidGameSizeException {
        if (players.length <= 1) {
            throw new InvalidGameSizeException();
        }
        this.players = players;
        drawPile = new Deck();
        dealBeginningHands();
        centerCard = drawPile.drawCard();
        int dealerIndex = decideDealer();
        dealer = players[dealerIndex];
        turnOrder = determineTurnOrder(dealerIndex);
        turnOrderDirection = TurnDirection.CLOCKWISE;
        status = GameStatus.BEGINNING;
    }*/

    // Getters and setters
    public Card getCenterCard() {
        return centerCard;
    }

    public Player getCurrentPlayer() {
        return turnOrder.peek();
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setGameStatus(GameStatus status) {
        this.status = status;
    }

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
     * Determines the turn order to start the game based on the dealer.
     *
     * @param dealerIndex index of the dealer in the players array.
     * @return LinkedList of the turn order. The player at the head goes first.
     */
    public LinkedList<Player> determineTurnOrder(int dealerIndex) {
        LinkedList<Player> temp = new LinkedList<>();

        // Pushing the players onto the LinkedList so that the turn order occurs in a clockwise fashion
        for (int i = dealerIndex; i < players.size(); i++) {
            temp.push(players.get(i));
        }

        // Making sure the dealer isn't the first person in the array to prevent an out-of-bounds error
        if (dealerIndex != 0) {
            for (int i = (dealerIndex - 1); i >= 0; i--) {
                temp.push(players.get(i));
            }
        }
        return temp;
    }

    /**
     * Advances the player turn order.
     *
     * @param skipped Checks if the player before played a skip card.
     */
    public void advanceTurnOrder(boolean skipped) {
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
    }

    /**
     * Checks if the game is over by seeing if any player has an empty hand.
     *
     * @return the Player who won the game. Null if nobody has won.
     */
    public Player gameOver() {
        for (Player p : players) {
            if (p.hasEmptyHand()) {
                return p;
            }
        }
        return null;
    }

    /**
     * Deals the beginning 7 cards to each player in the game.
     */
    private void dealBeginningHands() {
        for (int i = 0; i < 7; i++) {
            for (Player p : players) {
                p.getHand().add(drawPile.drawCard());
            }
        }
    }

    /**
     * Decides the dealer for the round by returning a random index value from the players array.
     *
     * @return the array index of the dealer.
     */
    private int decideDealer() {
        return (int) (Math.random() * players.size());
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
