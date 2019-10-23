package ru.otus.movieFinder.controllers;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.movieFinder.model.domain.ContentRelease;
import ru.otus.movieFinder.model.domain.Movie;
import ru.otus.movieFinder.model.dto.MovieRequestDTO;
import ru.otus.movieFinder.services.MovieService;
import ru.otus.movieFinder.services.ReleasesService;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
    private final ReleasesService releasesService;

    @PostMapping("/movies")
    @ApiOperation(value = "Получить список фильмов по части названия")
    public ResponseEntity<List<Movie>> listMoviesByTitle(@RequestBody @ApiParam(name = "movie", value = "часть названия фильма", required = true) MovieRequestDTO movie) {
        return ResponseEntity.ok().body(movieService.getMoviesByTitle(movie.getTitle()));
    }

    @GetMapping("/movies/{imdbId}")
    @ApiOperation(value = "Получить фильм по idmbId")
    public ResponseEntity<Movie> getMovieByImdbId(@PathVariable @ApiParam(value = "imdbId", example = "tt9243946") String imdbId) {
        return ResponseEntity.ok().body(movieService.getMovieByImdbId(imdbId));
    }

    @PostMapping("/releases/movies")
    @ApiOperation(value = "Получить список релизов фильмов по части названия")
    public ResponseEntity<List<ContentRelease>> listMovieReleasesByTitle(@RequestBody @ApiParam(name = "movie", value = "часть названия фильма", required = true) MovieRequestDTO movie) {
        return ResponseEntity.ok().body(releasesService.getMovieReleases(movie.getTitle()));
    }
}
