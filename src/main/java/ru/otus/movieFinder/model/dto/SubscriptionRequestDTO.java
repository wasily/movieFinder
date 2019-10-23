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
public class SubscriptionRequestDTO {
    @ApiModelProperty(value = "imdbId", required = true, example = "tt4270492")
    private String imdbId;

    @ApiModelProperty(value = "название фильма/сериала", required = true, example = "Billions")
    private String title;

    @ApiModelProperty(value = "email подписчика", required = true, example = "the-useless-box@mail.ru")
    private String userEmail;
}
