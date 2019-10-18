package ru.otus.movieFinder.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ru.otus.movieFinder.model.domain.Series;

import java.util.List;

public interface SeriesRepository extends MongoRepository<Series, String> {
    Series findByImdbId(String imdbId);
    @Query("{ 'primaryTitle' : {$regex:?0, $options:'i'}}")
    List<Series> findByPrimaryTitleContaining(String title);
}
