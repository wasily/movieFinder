package ru.otus.movieFinder.services;

import ru.otus.movieFinder.model.domain.ContentRelease;

import java.util.List;

public interface ReleasesService {
    List<ContentRelease> getMovieReleases(String title);
    List<ContentRelease> getSeriesReleases(String title);
}
