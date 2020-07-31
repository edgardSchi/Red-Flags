package me.shrix.cardapi.game;

import me.shrix.cardapi.game.exceptions.NoSuchCardException;
import java.util.HashMap;
import java.util.Random;

public class CardManager {

    public enum CardType {BLACK, RED};

    private static CardManager instance;

    //private ArrayList<Card> cards;
    private HashMap<Integer, BlackCard> blackCards;
    private HashMap<Integer, RedCard> redCards;

    private int idCounter = 0;

    public CardManager() {
        blackCards = new HashMap<Integer, BlackCard>();
        redCards = new HashMap<Integer, RedCard>();

        generateTestCards(50);
    }

/*    public static CardManager getInstance() {
        if(instance == null) {
            instance = new CardManager();
        }
        return instance;
    }*/

    /**
     * Adds a black card to the playable cards
     * @param card card to be added
     */
    public void addCard(BlackCard card) {
        blackCards.put(card.getId(), card);
    }

    /**
     * Adds a red card to the playable cards
     * @param card card to be added
     */
    public void addCard(RedCard card) {
        redCards.put(card.getId(), card);
    }

    public void addCard(CardType type, String content) {
        switch (type) {
            case RED:
                redCards.put(idCounter, new RedCard(idCounter, content));
                break;
            case BLACK:
                blackCards.put(idCounter, new BlackCard(idCounter, content));
                break;
            default:
                return;
        }
        idCounter++;
    }


    /**
     * Removes a card from the game
     * @param card card to be removed
     */
    public void removeCard(Card card) {
        if(card.getClass().equals(BlackCard.class)) {
            blackCards.remove(card);
        }
        if(card.getClass().equals(RedCard.class)) {
            redCards.remove(card);
        }
    }

    /**
     * Removes a card from the game
     * @param id id of that card
     */
    public void removeCard(int id) {
        blackCards.remove(id);
        redCards.remove(id);
    }

    /**
     * Returns a card based on its id
     * @param id id of the card
     * @return the given card
     * @throws NoSuchCardException if a card with this id does not exist
     */
    public Card getCard(int id) throws NoSuchCardException {
        Card c = blackCards.get(id);

        if(c == null) {
            c = redCards.get(id);
        }
        if(c == null) throw new NoSuchCardException(id);
        return c;
    }

    /**
     * Draw a black card. This card will be removed from the list of drawable cards
     * @return
     */
    public BlackCard drawBlackCard() {
        Random generator = new Random();
        Object[] values = blackCards.values().toArray();
        BlackCard card = (BlackCard) values[generator.nextInt(values.length)];

        blackCards.remove(card.getId());

        return card;
    }

    /**
     * Draw a red card. This card will be removed from the list of drawable cards
     * @return
     */
    public RedCard drawRedCard() {
        Random generator = new Random();
        Object[] values = redCards.values().toArray();
        RedCard card = (RedCard) values[generator.nextInt(values.length)];

        redCards.remove(card.getId());

        return card;
    }

    public CardType getCardType(int id) throws NoSuchCardException {
        if(redCards.containsKey(id)) return CardType.RED;
        else if(blackCards.containsKey(id)) return CardType.BLACK;
        else throw new NoSuchCardException(id);
    }

    /**
     * Generates n black and red testing cards. Will eventually be removed
     * @param n
     */
    private void generateTestCards(int n) {
        for(int i = 0; i < n; i++) {
            blackCards.put(i, new BlackCard((short) i, "Dies ist die schwarze Testkarte " + i));
            redCards.put(i, new RedCard((short) i, "Dies ist die rote Testkarte " + i));
            System.out.println(blackCards.get(i));
            System.out.println(redCards.get(i));
        }
    }

}
