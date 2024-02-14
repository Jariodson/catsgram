package ru.yandex.practicum.catsgram.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exceiption.ModelNotFoundException;
import ru.yandex.practicum.catsgram.model.Post;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PostService {
    private final List<Post> posts = new ArrayList<>();
    private final UserService userService;

    @Autowired
    public PostService(UserService userService) {
        this.userService = userService;
    }

    public Collection<Post> findAll(int size, String sort, int from) {
        log.info("Получен запрос GET для вывода постов");
        switch (sort) {
            case "asc":
                log.info("Вывод постов по возврастанию");
                return posts.subList(from, posts.size()).stream()
                        .sorted(Comparator.comparing(Post::getCreationDate))
                        .collect(Collectors.toList());
            case "desc":
                log.info("Вывод постов по убыванию");
                return posts.subList(from, posts.size()).stream()
                        .sorted((o1, o2) -> o2.getCreationDate().compareTo(o1.getCreationDate()))
                        .collect(Collectors.toList());
            default:
                log.warn("Ошибка! Неверный формат сортировки");
                throw new IllegalArgumentException();
        }
    }

    public Optional<Post> findById(int postId) {
        log.info("Получен запрос для поиска поста по ID: {}", postId);
        return posts.stream()
                .filter(x -> x.getId() == postId)
                .findFirst();
    }

    public Post create(Post post) {
        log.info("Получен зарпос /POST для создания поста: {}", post);
        if (userService.findUserByEmail(post.getAuthor()).isEmpty()) {
            log.warn("Ошибка создания поста! По адресу {} пользователь не найден!", post.getAuthor());
            throw new ModelNotFoundException("Пользователь" + post.getAuthor() + "не найден");
        }
        posts.add(post);
        log.info("Пост успешно создан! {}", post);
        return post;
    }
}
