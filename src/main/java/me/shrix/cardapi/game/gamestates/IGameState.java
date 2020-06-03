package me.shrix.cardapi.game.gamestates;

public interface IGameState {

    public GameStateManager.GameState getNextGameState();
    public void onCardPlayed(String userID, int cardID);
    public void onCardBlockChosen(String userID, int cardID);
    public void onStart();

}
