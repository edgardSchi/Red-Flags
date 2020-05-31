package me.shrix.cardapi.game;

import me.shrix.cardapi.db.models.Card;
import me.shrix.cardapi.db.models.Player;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Component
public class Game {

    enum GameState {LOBBY, CHOOSING_WHITE, CHOOSING_RED, CHOOSING_WINNER}



    //Player that has to choose a winner
    private Player currentPlayer;

    //private ArrayList<Player> players = new ArrayList<Player>();
    private HashMap<String, Player> players = new HashMap<String, Player>();
    private ArrayList<Card> cardsInGame = new ArrayList<Card>();
    private GameState currentGameState;

    public Game() {
        currentGameState = GameState.LOBBY;

        generateTestCards(50);
    }

    /**
     * Generates testing cards. Will eventually be removed
     * @param n
     */
    public void generateTestCards(int n) {
        for(int i = 0; i < n; i++) {
            cardsInGame.add(new Card((short) i, CardType.BLACK, "Dies ist die Testkarte " + i));
        }
    }

    public void addCard(Card card) {
        cardsInGame.add(card);
    }

    public ArrayList<Card> getCardsInGame() {
        return cardsInGame;
    }

    public boolean addPlayer(String id, String username) {
        return this.addPlayer(new Player(id, username));
    }

    public boolean addPlayer(Player player) {
        for(Player p : players.values()) {
            if(p.getUsername().equals(player.getUsername())) {
                System.out.println("Name equal to: " + p.getUsername());
                return false;
            }
        }
        players.put(player.getId(), player);
        return true;
    }

    public ArrayList<Player> getPlayers() {
        ArrayList<Player> list = new ArrayList<Player>();
        list.addAll(players.values());
        return list;
    }

    //Change the output
    public Card playCard(String userID, int cardId) {
        //Check if the player has the card
        Player player = players.get(userID);
        for(Card c : player.getCards()) {
            if(c.getId() == cardId) {
                player.getCards().remove(c);
                cardsInGame.remove(c);
                return c;
            }
        }

        return null;
    }

    public Card getCard(int id) {
        //Needs optimization
        for(Card c : cardsInGame) {
            if(c.getId() == id) {
                return c;
            }
        }

        return null;
    }

    public Player getPlayer(String id) {
        return players.get(id);
    }


    public Card drawCard(String userId) {
        int random = (int)(Math.random() * cardsInGame.size());

        Card card = cardsInGame.get(random);
        cardsInGame.remove(random);

        players.get(userId).getCards().add(card);

        System.out.println("Spieler " + players.get(userId).getUsername() + " hat Karte _" + card.getContent() + "_ gezogen!" + " | Random: " + random);
        return card;
    }
}
