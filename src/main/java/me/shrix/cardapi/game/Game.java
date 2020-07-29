package me.shrix.cardapi.game;

import me.shrix.cardapi.game.exceptions.NoSuchCardException;
import me.shrix.cardapi.game.exceptions.NoSuchPlayerException;
import me.shrix.cardapi.game.exceptions.UserIdTakenException;
import me.shrix.cardapi.game.exceptions.UsernameTakenException;
import me.shrix.cardapi.game.gamestates.GameStateManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Game {

    public static final int NUMBER_OF_RED_CARDS_IN_HAND = 6;
    public static final int NUMBER_OF_BLACK_CARDS_IN_HAND = 3;
    public static final int POINTS_TO_WIN = 3;

    //The current player, who chooses a card or needs to play a card
    private Player currentPlayer;

    private static Game instance;

    private GameStateManager gsm;
    private PlayerManager playerManager;
    private CardManager cardManager;

    private Game() {
        gsm = new GameStateManager();
        playerManager = PlayerManager.getInstance();
        cardManager = CardManager.getInstance();
    }

    public static Game getInstance() {
        if(instance == null) {
            instance = new Game();
        }
        return instance;
    }

    /**
     * Add a new black card to the game
     * @param content text on the card
     */
    public void addBlackCard(String content) {
        cardManager.addCard(CardManager.CardType.BLACK, content);
    }

    /**
     * Add a new red card to the game
     * @param content text on the card
     */
    public void addRedCard(String content) {
        cardManager.addCard(CardManager.CardType.RED, content);
    }

    public void addPlayer(String id, String username) throws UsernameTakenException, UserIdTakenException {
        playerManager.addPlayer(id, username);
    }

    public void addPlayer(Player player) throws UsernameTakenException, UserIdTakenException {
        playerManager.addPlayer(player);
    }

    public List<Player> getPlayers() {
        return playerManager.getPlayers();
    }

    //Change the output
    public void playCard(String userId, int cardId) throws NoSuchPlayerException, NoSuchCardException {
        Player player = playerManager.getPlayer(userId);

        if(!player.equals(currentPlayer)) return;

        if(player.hasCard(cardId)) {
            player.removeCard(cardId);
            System.out.println("Card " + cardManager.getCard(cardId).toString() + " was played by " + player.getUsername());
        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Card drawBlackCard() {
        return cardManager.drawBlackCard();
    }

    public Card drawRedCard() {
        return cardManager.drawRedCard();
    }

    private void everyPlayerDrawsBlackCard() {
        for (Player p : playerManager.getPlayers()) {
            p.addBlackCard(cardManager.drawBlackCard());
        }
    }

    private void everyPlayerDrawsRedCard() {
        for (Player p : playerManager.getPlayers()) {
            p.addRedCard(cardManager.drawRedCard());
        }
    }

    /**
     * Every player gets the maximum number of cards in his hands
     */
    public void drawFullHand() {
        for(Player p: playerManager.getPlayers()) {
            while (p.numberOfBlackCards() < NUMBER_OF_BLACK_CARDS_IN_HAND) {
                p.addBlackCard(cardManager.drawBlackCard());
            }
            while (p.numberOfRedCards() < NUMBER_OF_RED_CARDS_IN_HAND) {
                p.addRedCard(cardManager.drawRedCard());
            }
        }
    }

    public void nextState() {
        gsm.changeState();
    }

    public int getNumberOfPlayers() {
        return playerManager.getPlayers().size();
    }
}
