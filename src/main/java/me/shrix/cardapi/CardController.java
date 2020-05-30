package me.shrix.cardapi;

import me.shrix.cardapi.db.models.Card;
import me.shrix.cardapi.db.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CardController {

    @Autowired
    private CardRepository cards;

    @GetMapping("cards/getRandomCards")
    public List<Card> getRandomCards(@RequestParam("number") int number) {
        return cards.findRandomCards(number);
    }
}
