package me.shrix.cardapi.game.exceptions;

public class UsernameTakenException extends Exception {

    public UsernameTakenException(String name) {
        super("Username " + name + " is already taken!");
    }
}
