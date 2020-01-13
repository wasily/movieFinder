package ru.otus.movieFinder.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.movieFinder.model.domain.ContentRelease;
import ru.otus.movieFinder.model.domain.Movie;
import ru.otus.movieFinder.services.MovieService;
import ru.otus.movieFinder.services.ReleasesService;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
    private final ReleasesService releasesService;

    @GetMapping("/movies/title/{title:.+}")
    @ApiOperation(value = "Получить список фильмов по части названия")
    public ResponseEntity<List<Movie>> listMoviesByTitle(@PathVariable @ApiParam(value = "title", example = "Once Upon a Time", required = true) String title) {
        return ResponseEntity.ok().body(movieService.getMoviesByTitle(title));
    }

    @GetMapping("/movies/id/{imdbId}")
    @ApiOperation(value = "Получить фильм по idmbId")
    public ResponseEntity<Movie> getMovieByImdbId(@PathVariable @ApiParam(value = "imdbId", example = "tt9243946") String imdbId) {
        return ResponseEntity.ok().body(movieService.getMovieByImdbId(imdbId));
    }

    @GetMapping("/releases/movies/{title:.+}")
    @ApiOperation(value = "Получить список релизов фильмов по части названия")
    public ResponseEntity<List<ContentRelease>> listMovieReleasesByTitle(@PathVariable @ApiParam(value = "title", example = "Once Upon a Time...", required = true) String title) {
        return ResponseEntity.ok().body(releasesService.getMovieReleases(title));
    }
}
