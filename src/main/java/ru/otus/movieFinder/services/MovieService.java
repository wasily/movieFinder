package ru.otus.movieFinder.services;

import ru.otus.movieFinder.model.domain.Movie;

import java.time.LocalDate;
import java.util.List;

public interface MovieService {
    LocalDate getMovieReleaseDate(String imdb_id);
    List<Movie> getMoviesByTitle(String title);
    Movie getMovieByImdbId(String imdb_id);
}
