package ru.otus.movieFinder.services;

import ru.otus.movieFinder.model.domain.Subscription;

import java.util.List;

public interface SubscriptionService {
    boolean subscribeOnMovie(String imdbId, String title, String userEmail);
    boolean subscribeOnSeries(String imdbId, String title, String userEmail);
    List<Subscription> getUserSubscriptions(String userEmail);
    boolean unsubscribe(String imdbId, String userEmail);
}
