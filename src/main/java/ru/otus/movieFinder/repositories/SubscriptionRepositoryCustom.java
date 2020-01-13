package ru.otus.movieFinder.repositories;

public interface SubscriptionRepositoryCustom {
    boolean deleteSubscriptionByImdbIdAndUserEmail(String imdbId, String userEmail);
}
