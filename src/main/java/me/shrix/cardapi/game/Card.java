package me.shrix.cardapi.game;

public abstract class Card {

    protected int id;
    protected String content;

    public Card(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
