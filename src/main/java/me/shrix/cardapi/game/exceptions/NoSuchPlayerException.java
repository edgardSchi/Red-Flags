package me.shrix.cardapi.game.exceptions;

public class NoSuchPlayerException extends Exception {
    public NoSuchPlayerException(String id){
        super("Player with id " + id + " does not exist!");
    }
}
