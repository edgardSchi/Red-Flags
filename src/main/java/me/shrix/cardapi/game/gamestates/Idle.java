package me.shrix.cardapi.game.gamestates;

import me.shrix.cardapi.game.Player;
import me.shrix.cardapi.game.Game;

public class Idle implements IGameState {

    @Override
    public GameState getNextGameState() {

        Game.getInstance().drawFullHand();

        return GameState.PLAYING_BLACK;
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
