package ru.otus.movieFinder.repositories;

public interface SubscriptionRepositoryCustom {
    boolean deleteSubscriptionByImdbIdAndUser(String imdbId, String user);
}
