package ru.otus.movieFinder.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    @ApiOperation(value = "Подписаться на фильм")
    public ResponseEntity subscribeOnMovie(@RequestBody @ApiParam(name = "sub", value = "запрос на создание подписки на фильм", required = true) SubscriptionRequestDTO sub) {
        return subscriptionService.subscribeOnMovie(sub.getImdbId(), sub.getTitle(), sub.getUserEmail()) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @PostMapping("/subscriptions/series")
    @ApiOperation(value = "Подписаться на сериал")
    public ResponseEntity subscribeOnSeries(@RequestBody @ApiParam(name = "sub", value = "запрос на создание подписки на сериал", required = true) SubscriptionRequestDTO sub) {
        return subscriptionService.subscribeOnSeries(sub.getImdbId(), sub.getTitle(), sub.getUserEmail()) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/subscriptions/{userEmail:.+}")
    @ApiOperation(value = "Получить подписки определенного пользователя")
    public ResponseEntity<List<Subscription>> getUserSubscriptions(@PathVariable @ApiParam(value = "userEmail", example = "the-useless-box@mail.ru") String userEmail) {
        return ResponseEntity.ok().body(subscriptionService.getUserSubscriptions(userEmail));
    }

    @DeleteMapping("/subscriptions")
    @ApiOperation(value = "Отменить подписку")
    public ResponseEntity unsubscribe(@RequestBody @ApiParam(name = "sub", value = "запрос на отмену подписки", required = true) SubscriptionCancelDTO sub) {
        return subscriptionService.unsubscribe(sub.getImdbId(), sub.getUserEmail()) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
