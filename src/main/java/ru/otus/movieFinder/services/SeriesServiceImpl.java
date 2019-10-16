package ru.otus.movieFinder.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.movieFinder.model.domain.Series;
import ru.otus.movieFinder.repositories.SeriesRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeriesServiceImpl implements SeriesService {
    private final SeriesRepository seriesRepository;

    @Override
    public List<Series> getSeriesByTitle(String title) {
        return seriesRepository.findByPrimaryTitleContaining(title);
    }

    @Override
    public Series getSeriesByImdbId(String imdbId) {
        return seriesRepository.findByImdbId(imdbId);
    }
}
