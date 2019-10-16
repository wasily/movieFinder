package ru.otus.movieFinder.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.movieFinder.model.domain.Movie;

import java.util.List;

public interface MovieRepository extends MongoRepository<Movie, String> {
    Movie findByImdbId(String imdbId);
    List<Movie> findByPrimaryTitleContaining(String title);
}
