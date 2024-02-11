package ru.yandex.practicum.catsgram.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.exceiption.InvalidEmailException;
import ru.yandex.practicum.catsgram.exceiption.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final List<User> users = new ArrayList<>();

    @GetMapping("/users")
    public List<User> findAllUsers() {
        log.info("Кол-во пользователей: {}", users.size());
        return users;
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        if (user.getEmail() == null || user.getEmail().isBlank()) throw new InvalidEmailException("Неверный e-mail!");
        users.stream().filter(user1 -> user1.equals(user)).forEach(user1 -> {
            throw new UserAlreadyExistException("Пользватель с таким e-mail уже существует!");
        });
        users.add(user);
        log.info("Добавлен пользователь: {}", user);
        return user;
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {
        if (user.getEmail().isEmpty() || user.getEmail().isBlank()) throw new InvalidEmailException("Неверный e-mail!");
        for (User user1 : users){
            if (user.equals(user1)){
                users.set(users.indexOf(user1), user);
                return user;
            }
        }
        users.add(user);
        return user;
    }

}
