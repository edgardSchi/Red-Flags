package me.shrix.cardapi.game.gamestates;

import me.shrix.cardapi.game.Player;
import me.shrix.cardapi.game.Game;

public class NewTurn implements IGameState {


    public NewTurn() {

    }

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
        drawNewCards();
    }

    //Draw new cards at the beginning of a round if you don't have enough cards
    private void drawNewCards() {
        Game game = Game.getInstance();
        //Draw new red cards
        for(Player p : game.getPlayers()) {
            if(p.numberOfRedCards() < Game.NUMBER_OF_RED_CARDS_IN_HAND) {
                p.addCards(game.drawRedCards(Game.NUMBER_OF_RED_CARDS_IN_HAND - p.numberOfRedCards()));
            }
        }
        //Draw new black cards
        for(Player p : game.getPlayers()) {
            if(p.numberOfBlackCards() < Game.NUMBER_OF_BLACK_CARDS_IN_HAND) {
                p.addCards(game.drawBlackCards(Game.NUMBER_OF_BLACK_CARDS_IN_HAND - p.numberOfBlackCards()));
            }
        }
    }
}
