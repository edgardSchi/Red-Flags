package me.shrix.cardapi.game.gamestates;

import me.shrix.cardapi.game.Player;
import me.shrix.cardapi.game.Game;

public class Idle implements IGameState {

    @Override
    public GameState getNextGameState() {

        Game.getInstance().drawFullHand();

        return GameState.NEW_TURN;
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
