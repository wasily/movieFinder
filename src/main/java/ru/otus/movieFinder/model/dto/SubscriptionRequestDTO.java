package ru.otus.movieFinder.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubscriptionRequestDTO {
    private String imdb_id;
    private String user_email;
}
