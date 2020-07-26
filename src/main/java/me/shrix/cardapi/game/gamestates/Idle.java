package me.shrix.cardapi.game.gamestates;

import me.shrix.cardapi.db.models.Player;
import me.shrix.cardapi.game.Game;

public class Idle implements IGameState {

    @Override
    public GameState getNextGameState() {

        Game game = Game.getInstance();

        for(Player p : game.getPlayers()) {
            for(int i = 0; i < Game.NUMBER_OF_RED_CARDS_IN_HAND; i++) {
                p.addCard(game.drawRedCard());
            }
        }

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
