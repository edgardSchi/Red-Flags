package me.shrix.cardapi.db.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;

import java.util.ArrayList;
import java.util.List;

public class CardRepositoryImpl implements CardRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public CardRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

/*    @Override
    public List<Card> findRandomCards(int number) {
        List<AggregationOperation> list = new ArrayList<AggregationOperation>();
        list.add(Aggregation.sample(number));

        TypedAggregation<Card> agg = Aggregation.newAggregation(Card.class, list);
        return mongoTemplate.aggregate(agg, Card.class, Card.class).getMappedResults();
    }*/
}
