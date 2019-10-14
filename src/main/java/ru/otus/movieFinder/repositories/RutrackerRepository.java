package ru.otus.movieFinder.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.movieFinder.model.domain.ContentRelease;

import java.util.List;

public interface RutrackerRepository extends MongoRepository<ContentRelease, String> {
    List<ContentRelease> findByTitleContaining(String title);
}
