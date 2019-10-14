package ru.otus.movieFinder.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.movieFinder.model.domain.Subscription;
import ru.otus.movieFinder.repositories.SubscriptionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    @Override
    public boolean subscribeOnMovie(String imdb_id, String user_email) {
        return false;
    }

    @Override
    public boolean subscribeOnSeries(String imdb_id, String user_email) {
        return false;
    }

    @Override
    public List<Subscription> getUserSubscriptions(String user_emaild) {
        return null;
    }

    @Override
    public List<Subscription> getEmailsOfSubscription(String imdb_id) {
        return null;
    }

    @Override
    public boolean unsubscribe(String imdb_id, String user_email) {
        return false;
    }
}
