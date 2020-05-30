package me.shrix.cardapi.game;

import me.shrix.cardapi.db.models.Card;

import java.util.ArrayList;
import java.util.HashMap;

public class CardManager {

    private ArrayList<Card> cards;
    //private HashMap<Integer, Card> cards;

    public CardManager() {
        cards = new ArrayList<>();
    }

    /**
     * Adds a cards to the playable cards
     * @param card card to be added
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    //Mit welchem parameter remove? Card-Object?
    public void removeCard(Card card) {
        cards.remove(card);
    }

}
