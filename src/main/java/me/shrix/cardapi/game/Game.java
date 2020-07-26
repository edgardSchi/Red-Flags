package me.shrix.cardapi.game;

import me.shrix.cardapi.db.models.Card;
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

    //private HashMap<String, Player> players = new HashMap<String, Player>();
    private ArrayList<Card> blackCardsInGame = new ArrayList<Card>();
    private ArrayList<Card> redCardsInGame = new ArrayList<Card>();
    private GameStateManager gsm;
    private PlayerManager playerManager;

    private Game() {
        gsm = new GameStateManager();
        playerManager = PlayerManager.getInstance();
    }

    public static Game getInstance() {
        if(instance == null) {
            instance = new Game();
        }
        return instance;
    }


 /*   public void generateTestCards(int n) {
        for(int i = 0; i < n; i++) {
            blackCardsInGame.add(new Card((short) i, CardType.BLACK, "Dies ist die schwarze Testkarte " + i));
            redCardsInGame.add(new Card((short) i, CardType.RED, "Dies ist die rote Testkarte " + i));
            System.out.println(blackCardsInGame.get(i));
            System.out.println(redCardsInGame.get(i));
        }
    }*/

    public void addCard(Card card) {
        if (card.getCardType() == CardType.BLACK) {
            blackCardsInGame.add(card);
        } else {
            redCardsInGame.add(card);
        }
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
    public void playCard(String userId, int cardId) {
        Player player = playerManager.getPlayer(userId);

        if(!player.equals(currentPlayer)) return;

        //Check if the player has the card
        for(Card c : player.getCards()) {
            if(c.getId() == cardId) {
                player.getCards().remove(c);
                if(c.getCardType() == CardType.BLACK) {
                    blackCardsInGame.remove(c);
                } else {
                    redCardsInGame.remove(c);
                }
            }
        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /*public Player getPlayer(String id) {
        return players.get(id);
    }*/

    public Card drawBlackCard() {
        return drawCard(blackCardsInGame);
    }

    public Card[] drawBlackCards(int n) {
        Card[] cards = new Card[n];

        for(int i = 0; i < n; i++) {
            cards[i] = drawBlackCard();
        }

        return cards;
    }

    public Card[] drawRedCards(int n) {
        Card[] cards = new Card[n];

        for(int i = 0; i < n; i++) {
            cards[i] = drawRedCard();
        }

        return cards;
    }

    public Card drawRedCard() {
        return drawCard(redCardsInGame);
    }

    private Card drawCard(ArrayList<Card> list) {
        int random = (int)(Math.random() * list.size());

        Card card = list.get(random);
        list.remove(random);

        System.out.println("Karte _" + card.getContent() + "_ wurde gezogen!" + " | Random: " + random);
        return card;
    }

    public void everPlayerDrawsBlackCard() {
        everyPlayerDrawsCard(blackCardsInGame);
    }

    public void everPlayerDrawsRedCard() {
        everyPlayerDrawsCard(redCardsInGame);
    }

    private void everyPlayerDrawsCard(ArrayList<Card> list) {
        for (Player p : playerManager.getPlayers()) {
            p.addCard(drawCard(list));
        }
    }

    public void nextState() {
        gsm.changeState();
    }

    public int getNumberOfPlayers() {
        return playerManager.getPlayers().size();
    }
}
