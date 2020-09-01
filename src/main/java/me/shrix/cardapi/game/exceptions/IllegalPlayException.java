package me.shrix.cardapi.game.exceptions;

public class IllegalPlayException extends Exception {

    public IllegalPlayException() {
        super("Can't play that right now!");
    }

}
