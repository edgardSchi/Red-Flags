package me.shrix.cardapi.db.repositories;

import me.shrix.cardapi.db.models.Card;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CardRepository extends MongoRepository<Card, String>, CardRepositoryCustom {

    List<Card> findByCardType(@Param("cardtype") short cardType);
    //List<Card> findRandomCards(@Param("number") short number);
}
