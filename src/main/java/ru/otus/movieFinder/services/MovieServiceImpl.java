package ru.otus.movieFinder.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.movieFinder.model.domain.Movie;
import ru.otus.movieFinder.repositories.MovieRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    @Override
    public List<Movie> getMoviesByTitle(String title) {
        return movieRepository.findByPrimaryTitleContaining(title);
    }

    @Override
    public Movie getMovieByImdbId(String imdb_id) {
        return movieRepository.findByImdbId(imdb_id);
    }
}
