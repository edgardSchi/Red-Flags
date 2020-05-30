package me.shrix.cardapi;

import me.shrix.cardapi.db.models.Card;
import me.shrix.cardapi.db.models.Player;
import me.shrix.cardapi.webocketModels.ErrorModel;
import me.shrix.cardapi.webocketModels.UpdatePlayerCardsModel;
import me.shrix.cardapi.webocketModels.UpdatePlayersModel;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.ArrayList;
import java.util.Date;

@Service
public class GameService {
    private final SimpMessagingTemplate template;
    private static final String WS_MESSAGE_TRANSFER_DESTINATION = "/topic/players";

    GameService(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void sendMessage(String username) {
        template.convertAndSendToUser(username, WS_MESSAGE_TRANSFER_DESTINATION, HtmlUtils.htmlEscape("Hallo at " + new Date().toString()).toString());
    }

    //Send all players information to all users. Should me more efficient in the future
    public void updateConnectedPlayers(Player player) {
        System.out.println("Update player: " + player.getUsername());
        template.convertAndSend(WS_MESSAGE_TRANSFER_DESTINATION, new UpdatePlayersModel(player, UpdatePlayersModel.UpdateAction.PLAYER_CONNECTED));
    }

    public void sendInvalidUsername(String user) {
        template.convertAndSendToUser(user, "/topic/join", ErrorModel.ErrorType.USERNAME_TAKEN);
    }

    public void sendInvalidCardPlayed(String user) {
        template.convertAndSendToUser(user, "/topic/cards", ErrorModel.ErrorType.CARD_NOTFOUND);
    }

    public void sendAllPlayersToSinglePlayer(String user, ArrayList<Player> players) {
        template.convertAndSendToUser(user, "/topic/join", players);
    }

    public void sendPlayedCard(Card card) {
        template.convertAndSend("/topic/cards", card);
    }

    public void sendDrawnCard(String user, Card card) {
        template.convertAndSendToUser(user, "/topic/cards", new UpdatePlayerCardsModel(UpdatePlayerCardsModel.Intent.ADD_CARD, user, card));
    }

    public void sendDeleteCard(String user, Card card) {
        template.convertAndSendToUser(user, "/topic/cards", new UpdatePlayerCardsModel(UpdatePlayerCardsModel.Intent.DELETE_CARD, user, card));
    }
}
