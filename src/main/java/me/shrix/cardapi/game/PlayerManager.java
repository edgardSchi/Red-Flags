package me.shrix.cardapi.game;

import me.shrix.cardapi.db.models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayerManager {

    private static PlayerManager instance;

    private final HashMap<String, Player> players;
    private String currentPlayerId;

    private PlayerManager() {
        players = new HashMap<>();
    }

    public static PlayerManager getInstance() {
        if(instance == null) {
            instance = new PlayerManager();
        }
        return instance;
    }

    /**
     * Returns all current players of a game
     * @return current players as a List
     */
    public List<Player> getPlayers() {
        return new ArrayList<>(players.values());
    }

    /**
     * Adds a new player to the game
     * @param userId players id
     * @param username players name
     */
    public void addPlayer(String userId, String username) {
        players.put(userId, new Player(userId, username));
    }

    /**
     * Removes a player from the game
     * @param userId players id
     */
    public void removePlayer(String userId) {
        players.remove(userId);
    }

    /**
     * Returns the current player
     * @return current player
     */
    public Player getCurrentPlayer() {
        return players.get(currentPlayerId);
    }

    /**
     * Returns the current players id
     * @return current players id
     */
    public String getCurrentPlayersId() {
        return currentPlayerId;
    }

    /**
     * Sets the current players id
     * @param player
     */
    public void setCurrentPlayersId(Player player) {
        this.currentPlayerId = player.getId();
    }

    /**
     * Sets the current players id
     * @param id
     */
    public void setCurrentPlayersId(String id) {
        this.currentPlayerId = id;
    }

}
