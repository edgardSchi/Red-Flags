package me.shrix.cardapi.game.gamestates;

import me.shrix.cardapi.game.CardManager;
import me.shrix.cardapi.game.Game;
import me.shrix.cardapi.game.Player;
import me.shrix.cardapi.game.exceptions.NoSuchCardException;

import java.util.ArrayList;

public class PlayingBlack implements IGameState {

    private ArrayList<Player> playersNotPlayed;

    @Override
    public void onStart() {
        playersNotPlayed = new ArrayList<>();
        playersNotPlayed.addAll(Game.getInstance().getPlayers());
    }

    @Override
    public GameState getNextGameState() {
        return GameState.PLAYING_RED;
    }

    @Override
    public void onCardPlayed(Player player, int cardID) {
        try {
            if(Game.getInstance().getCardType(cardID) != CardManager.CardType.BLACK) {
                return;
            }
        } catch (NoSuchCardException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCardBlockChosen(Player player, int cardID) {

    }
}
