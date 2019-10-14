package ru.otus.movieFinder.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.movieFinder.model.domain.ContentRelease;
import ru.otus.movieFinder.model.domain.Movie;
import ru.otus.movieFinder.model.dto.MovieRequestDTO;
import ru.otus.movieFinder.services.MovieService;
import ru.otus.movieFinder.services.ReleasesService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
    private final ReleasesService releasesService;

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> listMovies(@RequestBody MovieRequestDTO movie) {
        return ResponseEntity.ok().body(movieService.getMoviesByTitle(movie.getTitle()));
    }

    @GetMapping("/movies/{imdb_id}")
    public ResponseEntity<Movie> getMovie(@PathVariable String imdb_id) {
        return ResponseEntity.ok().body(movieService.getMovieByImdbId(imdb_id));
    }

    @GetMapping("/releases/movies")
    public ResponseEntity<List<ContentRelease>> listMovieReleases(@RequestBody MovieRequestDTO movie) {
        return ResponseEntity.ok().body(releasesService.getMovieReleases(movie.getTitle()));
    }
}
