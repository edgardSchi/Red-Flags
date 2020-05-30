package me.shrix.cardapi.db.models;

import me.shrix.cardapi.game.CardType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

public class Card {



    private int id;

    private CardType cardType;
    private String content;

    public Card(int id, CardType cardType, String content) {
        this.id = id;
        this.cardType = cardType;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void SetCardType(CardType type) {
        this.cardType = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
