package me.shrix.cardapi;

import me.shrix.cardapi.db.models.Card;
import me.shrix.cardapi.db.models.Player;
import me.shrix.cardapi.game.Game;
import me.shrix.cardapi.game.exceptions.UserIdTakenException;
import me.shrix.cardapi.game.exceptions.UsernameTakenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;

@Controller
public class GameController {

    @Autowired
    private Game game;

    private final GameService gameService;
    private final SimpUserRegistry simpUserRegistry;

    GameController(GameService gameService, SimpUserRegistry simpUserRegistry) {
        this.gameService = gameService;
        this.simpUserRegistry = simpUserRegistry;
    }

/*    @MessageMapping("/card")
    @SendTo("/topic/greetins")
    public Card drawCard(String username) throws Exception {
        return new Card(0, 0,"Das ist eine Karte, für " + username);
    }*/

    /**
     * New player trying to join the game. User either gets a joining or a "username invalid" message
     * @param sha
     * @param username
     * @throws Exception
     */
    @MessageMapping("/join")
    public void joinGame(SimpMessageHeaderAccessor sha, String username) throws Exception {
        Player player = new Player(sha.getUser().getName(), username);

        try {
            game.addPlayer(player);
        } catch (UsernameTakenException ex) {
            gameService.sendInvalidUsername(sha.getUser().getName());
            return;
        } catch (UserIdTakenException ex) {
            gameService.sendInvalidUsername(sha.getUser().getName()); //Should create new message for invalid id
            return;
        }

        gameService.updateConnectedPlayers(player);
        gameService.sendAllPlayersToSinglePlayer(sha.getUser().getName(), new ArrayList(game.getPlayers()));
    }

    /* ### playCard method not working. Old way! ###
    @MessageMapping("/playCard")
    public void playCard(SimpMessageHeaderAccessor sha, int cardId) {
        Card card = game.playCard(sha.getUser().getName(), cardId);
        if(card != null) {
            gameService.sendPlayedCard(card);
        } else {
            gameService.sendInvalidCardPlayed(sha.getUser().getName());
        }
    }
     */

    /* ### drawCard method not working. Old way! ###
    @MessageMapping("/drawCard")
    public void drawCard(SimpMessageHeaderAccessor sha) throws Exception {
        Card card = game.drawCard(sha.getUser().getName());
        if(card != null) {
            gameService.sendDrawnCard(sha.getUser().getName(), card);
        }
    }
     */

}
