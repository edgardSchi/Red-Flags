package me.shrix.cardapi;

import me.shrix.cardapi.game.BlackCard;
import me.shrix.cardapi.game.Player;
import me.shrix.cardapi.game.Game;
import me.shrix.cardapi.game.RedCard;
import me.shrix.cardapi.game.exceptions.NoSuchPlayerException;
import me.shrix.cardapi.game.exceptions.UserIdTakenException;
import me.shrix.cardapi.game.exceptions.UsernameTakenException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GameTests {

    public static Game game = Game.getInstance();

    private void addPlayers(int n) throws UserIdTakenException, UsernameTakenException {
        for(int i = 0; i < n; i++) {
            game.addPlayer(Integer.toString(i), Integer.toString(i));
        }
    }

    /**
     * Tests if players are added correctly
     */
    @Test
    void addNewPlayer() throws UsernameTakenException, UserIdTakenException {
        game.resetGame();

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
    void drawCard() throws NoSuchPlayerException, UserIdTakenException, UsernameTakenException {
        game.resetGame();
        addPlayers(5);

        game.drawFullHand();
        for(Player p : game.getPlayers()) {
            assertEquals(Game.NUMBER_OF_BLACK_CARDS_IN_HAND, p.getNumberOfBlackCards());
            assertEquals(Game.NUMBER_OF_RED_CARDS_IN_HAND, p.getNumberOfRedCards());
        }

        game.drawFullHand();
        for(Player p : game.getPlayers()) {
            assertEquals(Game.NUMBER_OF_BLACK_CARDS_IN_HAND, p.getNumberOfBlackCards());
            assertEquals(Game.NUMBER_OF_RED_CARDS_IN_HAND, p.getNumberOfRedCards());
        }

        String id = "0";
        Player p = game.getPlayer(id);
        p.clearHand();
        BlackCard bCard = game.drawBlackCard();
        p.addBlackCard(bCard);
        assertEquals(p.getNumberOfCards(), 1);
        assertEquals(p.getNumberOfBlackCards(), 1);
        assertEquals(p.getNumberOfRedCards(), 0);

        RedCard rCard = game.drawRedCard();
        p.addRedCard(rCard);
        assertEquals(p.getNumberOfCards(), 2);
        assertEquals(p.getNumberOfBlackCards(), 1);
        assertEquals(p.getNumberOfRedCards(), 1);

        p.removeBlackCard(bCard.getId());
        p.removeRedCard(rCard.getId());
        assertEquals(p.getNumberOfCards(), 0);
        assertEquals(p.getNumberOfBlackCards(), 0);
        assertEquals(p.getNumberOfRedCards(), 0);
    }
}
