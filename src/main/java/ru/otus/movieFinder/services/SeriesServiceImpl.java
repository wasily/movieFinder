package ru.otus.movieFinder.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.movieFinder.model.domain.Series;
import ru.otus.movieFinder.repositories.SeriesRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeriesServiceImpl implements SeriesService {
    private final SeriesRepository seriesRepository;

    @Override
    public LocalDate getNextAiredEpisodeDate(String imdb_id) {
        return null;
    }

    @Override
    public List<Series> getSeriesByTitle(String title) {
        return null;
    }

    @Override
    public Series getSeriesByImdbId(String imdb_id) {
        return null;
    }
}
