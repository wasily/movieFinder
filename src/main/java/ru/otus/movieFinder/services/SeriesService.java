package ru.otus.movieFinder.services;

import ru.otus.movieFinder.model.domain.Series;

import java.time.LocalDate;
import java.util.List;

public interface SeriesService {
    LocalDate getNextAiredEpisodeDate(String imdb_id);
    List<Series> getSeriesByTitle(String title);
    Series getSeriesByImdbId(String imdb_id);
}
