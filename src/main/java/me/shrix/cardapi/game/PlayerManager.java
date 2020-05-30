package me.shrix.cardapi.game;

import me.shrix.cardapi.db.models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayerManager {

    private HashMap<String, Player> players;
    private String currentPlayerId;

    public PlayerManager() {
        players = new HashMap<String, Player>();
    }

    /**
     * Returns all current players of a game
     * @return current players as a List
     */
    public List<Player> getPlayers() {
        List<Player> list = new ArrayList<Player>();
        list.addAll(players.values());
        return list;
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


}
