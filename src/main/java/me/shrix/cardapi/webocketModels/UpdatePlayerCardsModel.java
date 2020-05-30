package me.shrix.cardapi.webocketModels;

import me.shrix.cardapi.db.models.Card;

public class UpdatePlayerCardsModel {

    public enum Intent {ADD_CARD, DELETE_CARD};

    private Intent intent;
    private String playerId;
    private Card updatedCard;

    public UpdatePlayerCardsModel(Intent intent, String userId, Card updatedCard) {
        this.playerId = playerId;
        this.updatedCard = updatedCard;
        this.intent = intent;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public Card getUpdatedCard() {
        return updatedCard;
    }

    public void setUpdatedCard(Card updatedCard) {
        this.updatedCard = updatedCard;
    }
}
