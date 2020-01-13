package ru.otus.movieFinder.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubscriptionMovieDTO {
    @ApiModelProperty(value = "imdbId", required = true, example = "tt6450804")
    private String imdbId;

    @ApiModelProperty(value = "название фильма", required = true, example = "Terminator: Dark Fate")
    private String title;

    @ApiModelProperty(value = "email подписчика", required = true, example = "the-useless-box@mail.ru")
    private String userEmail;
}
