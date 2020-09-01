package me.shrix.cardapi.game.gamestates;

import me.shrix.cardapi.game.Player;

public interface IGameState {

    public GameState getNextGameState();
    public void onCardPlayed(Player player, int cardID);
    public void onCardBlockChosen(Player player, int cardID);
    public void onStart();

}
