package ru.yandex.practicum.catsgram.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exceiption.UserNotFoundException;
import ru.yandex.practicum.catsgram.model.Post;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PostService {
    private final List<Post> posts = new ArrayList<>();
    private final UserService userService;

    @Autowired
    public PostService(UserService userService){
        this.userService = userService;
    }

    public List<Post> findAll() {
        log.info("Получен запрос /GET для вывода всех постов");
        log.info("Вывод всех постов");
        return posts;
    }

    public Post create(Post post) {
        log.info("Получен зарпос /POST для создания поста: {}", post);
        if (userService.findUserByEmail(post.getAuthor()) == null){
            log.warn("Ошибка создания поста! По адресу {} пользователь не найден!", post.getAuthor());
            throw new UserNotFoundException("Пользователь" + post.getAuthor() + "не найден");
        }
        posts.add(post);
        log.info("Пост успешно создан!");
        return post;
    }
}
