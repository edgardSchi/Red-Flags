package me.shrix.cardapi.game.gamestates;

public interface IGameState {

    public IGameState getNextGameState();
    public void onCardPlayed(String userID, int cardID);
    public void onCardBlockChosen(String userID, int cardID);

}
