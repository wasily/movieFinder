package ru.otus.movieFinder.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.movieFinder.model.domain.Movie;

public interface MovieRepository extends MongoRepository<Movie, String> {
    Movie findByImdbId(String imdb_id);
}
