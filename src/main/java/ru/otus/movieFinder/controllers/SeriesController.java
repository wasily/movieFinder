package ru.otus.movieFinder.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.movieFinder.model.domain.ContentRelease;
import ru.otus.movieFinder.model.domain.Series;
import ru.otus.movieFinder.model.dto.SeriesRequestDTO;
import ru.otus.movieFinder.services.ReleasesService;
import ru.otus.movieFinder.services.SeriesService;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
public class SeriesController {
    private final SeriesService seriesService;
    private final ReleasesService releasesService;

    @PostMapping("/series")
    @ApiOperation(value = "Получить список сериалов по части названия")
    public ResponseEntity<List<Series>> listSeriesByTitle(@RequestBody SeriesRequestDTO series) {
        return ResponseEntity.ok().body(seriesService.getSeriesByTitle(series.getTitle()));
    }

    @GetMapping("/series/{imdbId}")
    @ApiOperation(value = "Получить сериал по idmbId")
    public ResponseEntity<Series> getSeriesByImdbId(@PathVariable @ApiParam(value = "imdbId", example = "tt4643084") String imdbId) {
        return ResponseEntity.ok().body(seriesService.getSeriesByImdbId(imdbId));
    }

    @PostMapping("/releases/series")
    @ApiOperation(value = "Получить список релизов сериалов по части названия")
    public ResponseEntity<List<ContentRelease>> listSeriesReleasesByTitle(@RequestBody SeriesRequestDTO series) {
        return ResponseEntity.ok().body(releasesService.getSeriesReleases(series.getTitle()));
    }
}
