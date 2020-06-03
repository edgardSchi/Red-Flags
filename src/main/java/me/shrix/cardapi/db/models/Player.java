package me.shrix.cardapi.db.models;

import com.mongodb.lang.NonNull;
import me.shrix.cardapi.game.CardType;
import me.shrix.cardapi.game.Game;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class Player {

    enum Status {
        ACTIVE,
        INACTIVE
    }

    private String id;
    private String username;
    private List<Card> cards = new ArrayList<Card>();
    private Card[] blackCards;
    private Card[] redCards;
    private int points;
    private Status status;

    public Player(String id, String username) {
        this.id = id;
        this.username = username;
        points = 0;
        blackCards = new Card[Game.NUMBER_OF_BLACK_CARDS_IN_HAND];
        redCards = new Card[Game.NUMBER_OF_RED_CARDS_IN_HAND];
    }

    public Player(String id, String username, int points, ArrayList<Card> cards) {
        this.id = id;
        this.username = username;
        this.points = points;
        this.cards = cards;
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

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void addCard(Card card) {
        if(card.getCardType() == CardType.BLACK) {
            addCardToArray(blackCards, card);
        } else {
            addCardToArray(redCards, card);
        }
    }

    private void addCardToArray(Card[] arr, Card c) {
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == null) {
                arr[i] = c;
                return;
            }
        }
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }

    // ### NEEDS TO BE IMPLEMENTED ###
    private void removeCardFromArray(Card[] arr, Card c) {
        for(int i = 0; i < arr.length; i++) {
            if(arr[i].equals(c)) {

            }
        }
    }

    public int getNumberOfCards() {
        return cards.size();
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status s) {
        this.status = s;
    }
}
