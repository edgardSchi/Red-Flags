package me.shrix.cardapi;

import me.shrix.cardapi.game.Player;
import me.shrix.cardapi.game.Game;
import me.shrix.cardapi.game.exceptions.UserIdTakenException;
import me.shrix.cardapi.game.exceptions.UsernameTakenException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GameTests {

    public static Game game = Game.getInstance();

    /**
     * Tests if players are added correctly
     */
    @Test
    void addNewPlayer() throws UsernameTakenException, UserIdTakenException {

        game.addPlayer(new Player("0", "Jonas"));
        Assertions.assertThrows(UsernameTakenException.class, () -> {
            game.addPlayer(new Player("1", "Jonas"));
        });
        game.addPlayer("3", "Martha");
        Assertions.assertThrows(UserIdTakenException.class, () -> {
            game.addPlayer(new Player("0", "Noah"));
        });

        assertEquals(2, game.getNumberOfPlayers());

        game.addPlayer("4", "Adam");
        game.addPlayer(new Player("5", "Mikkel"));

        //game.nextState();
    }

    /**
     * Tests if cards are being drawn correctly
     * @throws UsernameTakenException
     * @throws UserIdTakenException
     */
    @Test
    void drawCard() throws UsernameTakenException, UserIdTakenException {
        game.drawFullHand();
        for(Player p : game.getPlayers()) {
            assertEquals(Game.NUMBER_OF_BLACK_CARDS_IN_HAND, p.numberOfBlackCards());
            assertEquals(Game.NUMBER_OF_RED_CARDS_IN_HAND, p.numberOfRedCards());
        }

        game.drawFullHand();
        for(Player p : game.getPlayers()) {
            assertEquals(Game.NUMBER_OF_BLACK_CARDS_IN_HAND, p.numberOfBlackCards());
            assertEquals(Game.NUMBER_OF_RED_CARDS_IN_HAND, p.numberOfRedCards());
        }
    }
}
