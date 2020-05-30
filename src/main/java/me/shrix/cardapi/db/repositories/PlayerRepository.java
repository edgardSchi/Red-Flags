package me.shrix.cardapi.db.repositories;

import me.shrix.cardapi.db.models.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlayerRepository extends MongoRepository<Player, String> {

    List<Player> findByUsername(@Param("username") String username);
}
