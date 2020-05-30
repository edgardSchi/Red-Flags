package me.shrix.cardapi.db.models;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String id;
    private String username;
    private List<Card> cards = new ArrayList<Card>();
    private int points;

    public Player(String id, String username) {
        this.id = id;
        this.username = username;
        points = 0;

        //Adding two cards for testing
/*        cards.add(new Card( 0, 0, "Dies ist eine Testkarte!"));
        cards.add(new Card( 1, 0, "Dies ist eine zweite Testkarte!"));*/
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

}
