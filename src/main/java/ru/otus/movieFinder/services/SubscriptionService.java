package ru.otus.movieFinder.services;

import ru.otus.movieFinder.model.domain.Subscription;

import java.util.List;

public interface SubscriptionService {
    boolean subscribeOnMovie(String imdb_id, String user_email);
    boolean subscribeOnSeries(String imdb_id, String user_email);
    List<Subscription> getUserSubscriptions(String user_email);
    List<Subscription> getEmailsOfSubscription(String imdb_id);
    boolean unsubscribe(String imdb_id, String user_email);
}
