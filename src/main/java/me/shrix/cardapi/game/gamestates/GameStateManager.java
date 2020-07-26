package me.shrix.cardapi.game.gamestates;

import me.shrix.cardapi.game.Game;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;

/**
 * Singleton class that handles the game states
 */
public class GameStateManager {

    private static EnumMap<GameState, IGameState> gameStates;
    private IGameState currentGameState;

    public GameStateManager(){
        gameStates = new EnumMap<GameState, IGameState>(GameState.class);
        initGameStates();
    };

    private void initGameStates() {
        gameStates.put(GameState.IDLE, new Idle());
        gameStates.put(GameState.PLAYING_RED, new PlayingRed());
        gameStates.put(GameState.PLAYING_BLACK, new PlayingBlack());
        gameStates.put(GameState.BACHELOR_CHOOSING, new BachelorChoosing());
        gameStates.put(GameState.NEW_TURN, new NewTurn());

        currentGameState = gameStates.get(GameState.IDLE);
    }

    public void changeState() {
        //gets the next game state and performs the last tasks of the current game state
        currentGameState = gameStates.get(currentGameState.getNextGameState());
        currentGameState.onStart();
    }
}
