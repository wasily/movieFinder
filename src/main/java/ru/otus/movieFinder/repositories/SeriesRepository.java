package ru.otus.movieFinder.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.movieFinder.model.domain.Series;

import java.util.List;

public interface SeriesRepository extends MongoRepository<Series, String> {
    Series findByImdbId(String imdbId);
    List<Series> findByPrimaryTitleContaining(String title);
}
