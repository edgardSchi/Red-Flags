package me.shrix.cardapi.game.gamestates;

import me.shrix.cardapi.game.Player;

public class PlayingRed implements IGameState {

    @Override
    public GameState getNextGameState() {
        return GameState.BACHELOR_CHOOSING;
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
