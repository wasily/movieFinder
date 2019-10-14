package ru.otus.movieFinder.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "series")
public class Series {
    @Field(value = "imdbId")
    private String imdbId;

    @Field(value = "primaryTitle")
    private String primaryTitle;

    @Field(value = "originalTitle")
    private String originalTitle;

    @Field(value = "isAdult")
    private boolean isAdult;

    @Field(value = "genres")
    private String genres;
}