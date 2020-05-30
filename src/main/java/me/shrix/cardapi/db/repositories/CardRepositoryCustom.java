package me.shrix.cardapi.db.repositories;

import me.shrix.cardapi.db.models.Card;

import java.util.List;

public interface CardRepositoryCustom {
    List<Card> findRandomCards(int number);
}
