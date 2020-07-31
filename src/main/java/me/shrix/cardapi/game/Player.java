package me.shrix.cardapi.game;

import com.mongodb.lang.NonNull;
import me.shrix.cardapi.game.Card;
import me.shrix.cardapi.game.CardType;
import me.shrix.cardapi.game.Game;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Player {

    enum Status {
        ACTIVE,
        INACTIVE
    }

    private String id;
    private String username;
    private HashMap<Integer, BlackCard> blackCards;
    private HashMap<Integer, RedCard> redCards;
    private int points;
    private Status status;

    public Player(String id, String username) {
        this.id = id;
        this.username = username;
        points = 0;
        blackCards = new HashMap<>();
        redCards = new HashMap<>();
    }

    public Player(String id, String username, int points) {
        this.id = id;
        this.username = username;
        this.points = points;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

/*    public void addCard(Card card) {
        if(card.)
        if(card.getCardType() == CardType.BLACK) {
            addCardToArray(blackCards, card);
        } else {
            addCardToArray(redCards, card);
        }
    }*/

/*    public void addCards(Card[] cards) {
        for(int i = 0; i < cards.length; i++) {
            addCard(cards[i]);
        }
    }*/

    public boolean addRedCard(RedCard card) {
        if(redCards.size() < Game.NUMBER_OF_RED_CARDS_IN_HAND) {
            redCards.put(card.id, card);
            return true;
        }
        return false;
    }

    public boolean addBlackCard(BlackCard card) {
        if(blackCards.size() < Game.NUMBER_OF_BLACK_CARDS_IN_HAND) {
            blackCards.put(card.id, card);
            return true;
        }
        return false;
    }

    private void addCardToArray(Card[] arr, Card c) {
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == null) {
                arr[i] = c;
                return;
            }
        }
    }

    public void removeBlackCard(int id) {
        blackCards.remove(id);
    }

    public void removeRedCard(int id) {
        redCards.remove(id);
    }

    public void removeCard(int id) {
        redCards.remove(id);
        redCards.remove(id);
    }

    /**
     * Removes all cards from the players hand
     */
    public void clearHand() {
        redCards.clear();
        blackCards.clear();
    }

    /**
     * Returns whether the player has the card associated with a certain id.
     * @param id id of the card
     * @return true if player has card, false otherwise
     */
    public boolean hasCard(int id) {
        return blackCards.containsKey(id) | redCards.containsKey(id);
    }

    public int getNumberOfBlackCards() {
        return blackCards.size();
    }

    public int getNumberOfRedCards() {
        return redCards.size();
    }

    public int getNumberOfCards() {
        return getNumberOfBlackCards() + getNumberOfRedCards();
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status s) {
        this.status = s;
    }
}
