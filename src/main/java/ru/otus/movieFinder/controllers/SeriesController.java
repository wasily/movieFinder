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
    public ResponseEntity<List<Series>> listSeries(@RequestBody SeriesRequestDTO series) {
        return ResponseEntity.ok().body(seriesService.getSeriesByTitle(series.getTitle()));
    }

    @GetMapping("/series/{imdb_id}")
    public ResponseEntity<Series> getSeries(@PathVariable String imdb_id) {
        return ResponseEntity.ok().body(seriesService.getSeriesByImdbId(imdb_id));
    }

    @GetMapping("/releases/series")
    public ResponseEntity<List<ContentRelease>> listSeriesReleases(@RequestBody SeriesRequestDTO series) {
        return ResponseEntity.ok().body(releasesService.getSeriesReleases(series.getTitle()));
    }
}
