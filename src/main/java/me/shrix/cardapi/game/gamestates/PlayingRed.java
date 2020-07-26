package me.shrix.cardapi.game.gamestates;

public class PlayingRed implements IGameState {

    @Override
    public GameState getNextGameState() {
        return null;
    }

    @Override
    public void onCardPlayed(String userID, int cardID) {

    }

    @Override
    public void onCardBlockChosen(String userID, int cardID) {

    }

    @Override
    public void onStart() {

    }
}
