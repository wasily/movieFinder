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
public class MovieRequestDTO {
    @ApiModelProperty(value = "часть названия фильма", required = true, example = "terminator")
    private String title;
}
