package ru.otus.movieFinder.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.movieFinder.model.domain.ContentRelease;
import ru.otus.movieFinder.repositories.RutrackerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RutrackerReleasesServiceImpl implements ReleasesService {
    private final RutrackerRepository rutrackerRepository;

    @Override
    public List<ContentRelease> getMovieReleases(String title) {
        return rutrackerRepository.findByTitleContaining(title);
    }

    @Override
    public List<ContentRelease> getSeriesReleases(String title) {
        return null;
    }
}
