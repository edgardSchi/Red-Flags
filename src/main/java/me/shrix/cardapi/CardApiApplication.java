package me.shrix.cardapi;

import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import me.shrix.cardapi.db.models.Card;
import me.shrix.cardapi.db.repositories.CardRepository;
import me.shrix.cardapi.db.repositories.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;

@SpringBootApplication
public class CardApiApplication {

	final Logger logger = LoggerFactory.getLogger(getClass());

	//Populate the database with testing cards and deleting old users
/*	@Autowired
	private CardRepository cardRepo;
	@Autowired
	private PlayerRepository playerRepo;

	@Bean
	CommandLineRunner preLoadMongo() throws Exception {
		return args -> {
			playerRepo.deleteAll();
			cardRepo.deleteAll();
			for(int i = 1; i < 50; i++) {
				cardRepo.save(new Card((short) 0, "Karte " + i));
			}
		};
	}*/

	public static void main(String[] args) {
		SpringApplication.run(CardApiApplication.class, args);
	}
}
