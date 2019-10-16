package ru.otus.movieFinder.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.movieFinder.model.domain.Subscription;

import java.util.List;

public interface SubscriptionRepository extends MongoRepository<Subscription, String>, SubscriptionRepositoryCustom {
    List<Subscription> findByUser(String user);
}
