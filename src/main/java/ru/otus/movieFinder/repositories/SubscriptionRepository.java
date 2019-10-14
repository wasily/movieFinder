package ru.otus.movieFinder.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.movieFinder.model.domain.Subscription;

public interface SubscriptionRepository extends MongoRepository<Subscription, String> {
}
