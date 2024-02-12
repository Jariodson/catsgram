package ru.yandex.practicum.catsgram.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Data
public class Post {

    private final String author; // автор
    private final Instant creationDate = Instant.now(); // дата создания
    private String description; // описание
    @Setter
    private String photoUrl; // url-адрес фотографии

    public Post(String author, String description, String photoUrl) {
        this.author = author;
        this.description = description;
        this.photoUrl = photoUrl;
    }

}