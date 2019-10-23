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
public class SeriesRequestDTO {
    @ApiModelProperty(value = "часть названия сериала", required = true, example = "silicon valley")
    private String title;
}
