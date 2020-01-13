package ru.otus.movieFinder.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ru.otus.movieFinder.model.domain.Movie;

import java.util.List;

public interface MovieRepository extends MongoRepository<Movie, String> {
    Movie findByImdbId(String imdbId);

    @Query(value = "{ 'primaryTitle' : {$regex:?0, $options:'i'}}", sort = "{\"startYear\" : -1}")
    List<Movie> findByPrimaryTitleContaining(String title);
}
