package ru.otus.movieFinder.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "subscriptions")
public class Subscription {
    @Field(value = "imdbId")
    private String imdbId;

    @Field(value = "content_type")
    private String content_type;

    @Field(value = "content_title")
    private String content_title;

    @Field(value = "users_emails")
    private List<String> users_emails;

    @Field(value = "search_date")
    private LocalDate search_date;

    @Field(value = "next_episode_search_date")
    private LocalDate next_episode_search_date;
}
