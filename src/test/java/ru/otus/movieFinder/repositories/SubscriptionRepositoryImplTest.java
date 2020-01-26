package ru.otus.movieFinder.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.movieFinder.model.domain.Subscription;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataMongoTest
@DisplayName("Тест провреки репозитария подписок")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class SubscriptionRepositoryImplTest {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Test
    @DisplayName("Должен корректно удалить подписку по \"imdbId\" и \"userEmail\" ({\"imdbId\" : 1, \"userEmail\" : 1}, { unique : true })")
    void shouldDeleteSubscriptionByImdbIdAndUserEmail() {
        String imdbId = "imdbId";
        String userEmail = "test@test.test";

        assertFalse(subscriptionRepository.deleteSubscriptionByImdbIdAndUserEmail(null, userEmail));
        assertFalse(subscriptionRepository.deleteSubscriptionByImdbIdAndUserEmail(imdbId, null));

        assertThat(subscriptionRepository.findAll()).hasSize(0);
        Subscription subscription = new Subscription(imdbId, "contentType", "title", userEmail,
                LocalDateTime.of(2020, 1, 30, 11, 25));
        subscriptionRepository.save(subscription);
        assertThat(subscriptionRepository.findAll()).hasSize(1);

        assertTrue(subscriptionRepository.deleteSubscriptionByImdbIdAndUserEmail(imdbId, userEmail));
        assertThat(subscriptionRepository.findAll()).hasSize(0);
    }

}