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
@Document(collection = "content_releases")
public class ContentRelease {
    @Field(value = "title")
    private String title;

    @Field(value = "size")
    private long size;

    @Field(value = "info_hash")
    private String info_hash;

    @Field(value = "reg_time")
    private long reg_time;
}
