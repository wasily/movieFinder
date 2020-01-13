package ru.otus.movieFinder.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ru.otus.movieFinder.model.domain.Subscription;

@RequiredArgsConstructor
public class SubscriptionRepositoryCustomImpl implements SubscriptionRepositoryCustom {
    private final MongoTemplate mongoTemplate;

    @Override
    public boolean deleteSubscriptionByImdbIdAndUserEmail(String imdbId, String userEmail) {
        mongoTemplate.remove(Query.query(Criteria.where("imdbId").is(imdbId).and("userEmail").is(userEmail)), Subscription.class);
        return true;
    }
}
