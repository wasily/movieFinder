package ru.otus.movieFinder.services;

import ru.otus.movieFinder.model.domain.Subscription;

import java.util.List;

public interface SubscriptionService {
    boolean subscribeOnMovie(String imdbId, String title, String user);
    boolean subscribeOnSeries(String imdbId, String title, String user);
    List<Subscription> getUserSubscriptions(String user);
    boolean unsubscribe(String imdbId, String user);
}
