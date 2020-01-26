package ru.otus.movieFinder.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.movieFinder.model.domain.ContentRelease;
import ru.otus.movieFinder.model.domain.Movie;
import ru.otus.movieFinder.services.MovieService;
import ru.otus.movieFinder.services.ReleasesService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MovieController.class)
@DisplayName("Тест проверки контроллера фильмов")
class MovieControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    @MockBean
    private ReleasesService releasesService;

    @Test
    @DisplayName("должен вернуть список фильмов по части названия")
    void shouldReturnMoviesByTitle() throws Exception {
        List<Movie> result = new ArrayList<>();
        String title = "test";
        result.add(new Movie("imdbId", title, "originalTitle", false, 1984, List.of("genre1", "genre2")));
        when(movieService.getMoviesByTitle(title)).thenReturn(result);
        mockMvc.perform(get("/movies/title/test"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].imdbId", is(result.get(0).getImdbId())))
                .andExpect(jsonPath("$[0].primaryTitle", is(result.get(0).getPrimaryTitle())))
                .andExpect(jsonPath("$[0].originalTitle", is(result.get(0).getOriginalTitle())))
                .andExpect(jsonPath("$[0].isAdult", is(result.get(0).getIsAdult())))
                .andExpect(jsonPath("$[0].startYear", is(result.get(0).getStartYear())))
                .andExpect(jsonPath("$[0].genres", allOf(hasSize(result.get(0).getGenres().size()), contains(result.get(0).getGenres().toArray()))));
    }

    @Test
    @DisplayName("должен вернуть фильм по imdbId")
    void shouldReturnMovieByImdbId() throws Exception {
        String imdbId = "imdbId";
        Movie test = new Movie(imdbId, "primaryTitle", "originalTitle", false, 1984, List.of("genre1", "genre2"));
        when(movieService.getMovieByImdbId(imdbId)).thenReturn(test);
        mockMvc.perform(get("/movies/id/" + imdbId))
                .andExpect(jsonPath("$.imdbId", is(test.getImdbId())))
                .andExpect(jsonPath("$.primaryTitle", is(test.getPrimaryTitle())))
                .andExpect(jsonPath("$.originalTitle", is(test.getOriginalTitle())))
                .andExpect(jsonPath("$.isAdult", is(test.getIsAdult())))
                .andExpect(jsonPath("$.startYear", is(test.getStartYear())))
                .andExpect(jsonPath("$.genres", allOf(hasSize(test.getGenres().size()), contains(test.getGenres().toArray()))));
    }

    @Test
    @DisplayName("должен вернуть релизы фильмов по части названия")
    void shouldReturnMovieReleasesByTitle() throws Exception {
        List<ContentRelease> result = new ArrayList<>();
        String title = "test";
        result.add(new ContentRelease("trackerId", "contentType", title, 0, "34567565875uyg67",
                LocalDateTime.of(2015, 1, 1, 15, 20, 59)));
        when(releasesService.getMovieReleases(title)).thenReturn(result);
        mockMvc.perform(get("/releases/movies/" + title))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].trackerId", is(result.get(0).getTrackerId())))
                .andExpect(jsonPath("$[0].contentType", is(result.get(0).getContentType())))
                .andExpect(jsonPath("$[0].title", is(result.get(0).getTitle())))
                .andExpect(jsonPath("$[0].size", is(result.get(0).getSize()), Long.class))
                .andExpect(jsonPath("$[0].infoHash", is(result.get(0).getInfoHash())))
                .andExpect(jsonPath("$[0].regTime", is(result.get(0).getRegTime().toString())));
    }
}