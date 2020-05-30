package me.shrix.cardapi.webocketModels;

import me.shrix.cardapi.db.models.Player;

public class UpdatePlayersModel {

    //Action that caused a player update; DISCONNECTED, CONNECTED, GOTPOINT
    public enum UpdateAction {PLAYER_CONNECTED};

    private Player player;
    private UpdateAction updateAction;

    public UpdatePlayersModel(Player player, UpdateAction updateAction) {
        this.player = player;
        this.updateAction = updateAction;
    }

    public Player getPlayer() {
        return player;
    }

    public UpdateAction getAction() {
        return updateAction;
    }
}
