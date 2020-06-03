package me.shrix.cardapi.game.gamestates;

import me.shrix.cardapi.db.models.Player;
import me.shrix.cardapi.game.Game;

public class NewTurn implements IGameState {

    private Game game;

    public NewTurn() {
        game = Game.getInstance();
    }

    @Override
    public GameStateManager.GameState getNextGameState() {
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

    //Draw new cards at the beginning of a round if you don't have enough cards
    private void drawNewCards() {
        for(Player p : game.getPlayers()) {

        }
    }
}
