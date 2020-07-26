package me.shrix.cardapi.game.exceptions;

public class UserIdTakenException extends Exception {

    public UserIdTakenException(String id) {
        super("UserId " + id + " is already taken!");
    }
}
