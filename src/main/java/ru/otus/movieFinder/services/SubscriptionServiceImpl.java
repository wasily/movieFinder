package ru.otus.movieFinder.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.movieFinder.model.domain.Subscription;
import ru.otus.movieFinder.repositories.SubscriptionRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private static final String MOVIE_CONTENT_TYPE = "movie";
    private static final String SERIES_CONTENT_TYPE = "series";

    @Override
    public boolean subscribeOnMovie(String imdbId, String title, String userEmail) {
        subscriptionRepository.save(new Subscription(imdbId, MOVIE_CONTENT_TYPE, title, userEmail, LocalDateTime.now()));
        return true;
    }

    @Override
    public boolean subscribeOnSeries(String imdbId, String title, String userEmail) {
        subscriptionRepository.save(new Subscription(imdbId, SERIES_CONTENT_TYPE, title, userEmail, LocalDateTime.now()));
        return true;
    }

    @Override
    public List<Subscription> getUserSubscriptions(String userEmail) {
        return subscriptionRepository.findByUserEmail(userEmail);
    }

    @Override
    public boolean unsubscribe(String imdbId, String userEmail) {
        return subscriptionRepository.deleteSubscriptionByImdbIdAndUserEmail(imdbId, userEmail);
    }
}
