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
import ru.otus.movieFinder.model.domain.Series;
import ru.otus.movieFinder.services.ReleasesService;
import ru.otus.movieFinder.services.SeriesService;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
public class SeriesController {
    private final SeriesService seriesService;
    private final ReleasesService releasesService;

    @GetMapping("/series/title/{title:.+}")
    @ApiOperation(value = "Получить список сериалов по части названия")
    public ResponseEntity<List<Series>> listSeriesByTitle(@PathVariable @ApiParam(value = "title", example = "house", required = true) String title) {
        return ResponseEntity.ok().body(seriesService.getSeriesByTitle(title));
    }

    @GetMapping("/series/id/{imdbId}")
    @ApiOperation(value = "Получить сериал по idmbId")
    public ResponseEntity<Series> getSeriesByImdbId(@PathVariable @ApiParam(value = "imdbId", example = "tt4643084", required = true) String imdbId) {
        return ResponseEntity.ok().body(seriesService.getSeriesByImdbId(imdbId));
    }

    @GetMapping("/releases/series/{title:.+}")
    @ApiOperation(value = "Получить список релизов сериалов по части названия")
    public ResponseEntity<List<ContentRelease>> listSeriesReleasesByTitle(@PathVariable @ApiParam(value = "title", example = "silicon valley", required = true) String title) {
        return ResponseEntity.ok().body(releasesService.getSeriesReleases(title));
    }
}
