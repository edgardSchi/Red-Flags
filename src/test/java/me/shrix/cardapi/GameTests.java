package me.shrix.cardapi;

import me.shrix.cardapi.db.models.Player;
import me.shrix.cardapi.game.Game;
import me.shrix.cardapi.game.exceptions.UserIdTakenException;
import me.shrix.cardapi.game.exceptions.UsernameTakenException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GameTests {

    public static Game game = Game.getInstance();

    /**
     * Tests if players are added correctly
     */
    @Test
    void addNewPlayer() throws UsernameTakenException, UserIdTakenException {

        game.addPlayer(new Player("0", "foo"));
        Assertions.assertThrows(UsernameTakenException.class, () -> {
            game.addPlayer(new Player("1", "foo"));
        });
        game.addPlayer("3", "eddy");
        Assertions.assertThrows(UserIdTakenException.class, () -> {
            game.addPlayer(new Player("0", "bar"));
        });

        assertEquals(2, game.getNumberOfPlayers());
    }


    void drawCard() throws UsernameTakenException, UserIdTakenException {
        game.addPlayer(new Player("0", "foo"));
        game.generateTestCards(10);
        game.everPlayerDrawsBlackCard();
        //assertNotNull(game.getPlayer("0").getCards());
    }
}
