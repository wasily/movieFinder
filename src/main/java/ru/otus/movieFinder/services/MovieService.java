package ru.otus.movieFinder.services;

import ru.otus.movieFinder.model.domain.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getMoviesByTitle(String title);
    Movie getMovieByImdbId(String imdbId);
}
