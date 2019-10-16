package ru.otus.movieFinder.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.movieFinder.model.domain.Subscription;
import ru.otus.movieFinder.model.dto.SubscriptionRequestDTO;
import ru.otus.movieFinder.services.SubscriptionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @PostMapping("/subscribe/movies")
    public ResponseEntity subscribeOnMovie(@RequestBody SubscriptionRequestDTO sub) {
        return subscriptionService.subscribeOnMovie(sub.getImdbId(), sub.getTitle(), sub.getUser()) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @PostMapping("/subscribe/series")
    public ResponseEntity subscribeOnSeries(@RequestBody SubscriptionRequestDTO sub) {
        return subscriptionService.subscribeOnSeries(sub.getImdbId(), sub.getTitle(), sub.getUser()) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/subscriptions/{user}")
    public ResponseEntity<List<Subscription>> getUserSubscriptions(@PathVariable String user) {
        return ResponseEntity.ok().body(subscriptionService.getUserSubscriptions(user));
    }

    @DeleteMapping("/subscriptions")
    public ResponseEntity unsubscribe(@RequestBody SubscriptionRequestDTO sub) {
        return subscriptionService.unsubscribe(sub.getImdbId(), sub.getUser()) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
