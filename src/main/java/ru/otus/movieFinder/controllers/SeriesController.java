package ru.otus.movieFinder.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.movieFinder.model.domain.ContentRelease;
import ru.otus.movieFinder.model.domain.Series;
import ru.otus.movieFinder.model.dto.SeriesRequestDTO;
import ru.otus.movieFinder.services.ReleasesService;
import ru.otus.movieFinder.services.SeriesService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SeriesController {
    private final SeriesService seriesService;
    private final ReleasesService releasesService;

    @GetMapping("/series")
    public ResponseEntity<List<Series>> listSeriesByTitle(@RequestBody SeriesRequestDTO series) {
        return ResponseEntity.ok().body(seriesService.getSeriesByTitle(series.getTitle()));
    }

    @GetMapping("/series/{imdbId}")
    public ResponseEntity<Series> getSeriesByImdbId(@PathVariable String imdbId) {
        return ResponseEntity.ok().body(seriesService.getSeriesByImdbId(imdbId));
    }

    @GetMapping("/releases/series")
    public ResponseEntity<List<ContentRelease>> listSeriesReleasesByTitle(@RequestBody SeriesRequestDTO series) {
        return ResponseEntity.ok().body(releasesService.getSeriesReleases(series.getTitle()));
    }
}
