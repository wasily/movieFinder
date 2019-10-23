package ru.otus.movieFinder.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.movieFinder.model.domain.Subscription;
import ru.otus.movieFinder.model.dto.SubscriptionCancelDTO;
import ru.otus.movieFinder.model.dto.SubscriptionRequestDTO;
import ru.otus.movieFinder.services.SubscriptionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @PostMapping("/subscriptions/movies")
    public ResponseEntity subscribeOnMovie(@RequestBody SubscriptionRequestDTO sub) {
        return subscriptionService.subscribeOnMovie(sub.getImdbId(), sub.getTitle(), sub.getUserEmail()) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @PostMapping("/subscriptions/series")
    public ResponseEntity subscribeOnSeries(@RequestBody SubscriptionRequestDTO sub) {
        return subscriptionService.subscribeOnSeries(sub.getImdbId(), sub.getTitle(), sub.getUserEmail()) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/subscriptions/{userEmail:.+}")
    public ResponseEntity<List<Subscription>> getUserSubscriptions(@PathVariable String userEmail) {
        return ResponseEntity.ok().body(subscriptionService.getUserSubscriptions(userEmail));
    }

    @DeleteMapping("/subscriptions")
    public ResponseEntity unsubscribe(@RequestBody SubscriptionCancelDTO sub) {
        return subscriptionService.unsubscribe(sub.getImdbId(), sub.getUserEmail()) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
