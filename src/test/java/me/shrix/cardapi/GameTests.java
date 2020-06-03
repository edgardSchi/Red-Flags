package me.shrix.cardapi;

import me.shrix.cardapi.db.models.Player;
import me.shrix.cardapi.game.Game;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GameTests {

    public static Game game = Game.getInstance();

    //Tests if the game adds new players the right way
    @Test
    void addNewPlayer() {
        assertEquals(true, game.addPlayer(new Player("0", "foo")));
        assertEquals(false, game.addPlayer(new Player("1", "foo")));
        game.nextState();
    }


    void drawCard() {
        assertEquals(true, game.addPlayer(new Player("0", "foo")));
        game.generateTestCards(10);
        game.everPlayerDrawsBlackCard();
        assertNotNull(game.getPlayer("0").getCards());
    }
}
