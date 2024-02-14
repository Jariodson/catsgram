package ru.yandex.practicum.catsgram.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Data
@Builder
public class Post {
    private Integer id;
    private final String author; // автор
    private final Instant creationDate = Instant.now(); // дата создания
    private String description; // описание
    private String photoUrl; // url-адрес фотографии
}