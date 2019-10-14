package ru.otus.movieFinder.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.movieFinder.model.domain.Series;

public interface SeriesRepository extends MongoRepository<Series, String> {
}
