package me.shrix.cardapi.game.gamestates;

import me.shrix.cardapi.game.Player;

public class BachelorChoosing implements IGameState{
    @Override
    public GameState getNextGameState() {
        return null;
    }

    @Override
    public void onCardPlayed(Player player, int cardID) {

    }

    @Override
    public void onCardBlockChosen(Player player, int cardID) {

    }

    @Override
    public void onStart() {

    }
}
