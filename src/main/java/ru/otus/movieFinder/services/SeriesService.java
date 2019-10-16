package ru.otus.movieFinder.services;

import ru.otus.movieFinder.model.domain.Series;

import java.util.List;

public interface SeriesService {
    List<Series> getSeriesByTitle(String title);
    Series getSeriesByImdbId(String imdbId);
}
