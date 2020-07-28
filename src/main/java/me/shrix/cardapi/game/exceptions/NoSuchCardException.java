package me.shrix.cardapi.game.exceptions;

public class NoSuchCardException extends Exception {
    public NoSuchCardException(int id) {
        super("Card with id " + id + " does not exist!");
    }
}
