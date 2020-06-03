package me.shrix.cardapi.game.gamestates;

/**
 * Singleton class that handles the game states
 */
public class GameStateManager {

    private static GameStateManager instance;

    private GameStateManager(){};

    public static GameStateManager getInstance() {
        if(instance == null) {
            instance = new GameStateManager();
        }
        return instance;
    }


}
