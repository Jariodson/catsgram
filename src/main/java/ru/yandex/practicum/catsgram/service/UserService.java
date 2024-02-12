package ru.yandex.practicum.catsgram.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exceiption.InvalidEmailException;
import ru.yandex.practicum.catsgram.exceiption.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserService {
    private final List<User> users = new ArrayList<>();

    public List<User> getUsers (){
        log.info("Получен запрос /GET на получение всех пользователей");
        log.info("Вывод пользователей");
        return users;
    }

    public User createUser(User user){
        log.info("Получен запрос /POST на создание нового пользователя");
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            log.warn("Ошибка! Введен некоректный email");
            throw new InvalidEmailException("Неверный e-mail!");
        }
        users.stream().filter(user1 -> user1.equals(user)).forEach(user1 -> {
            log.warn("Ошибка! Пользователь с email: {} уже существует", user.getEmail());
            throw new UserAlreadyExistException("Пользватель с таким e-mail уже существует!");
        });
        users.add(user);
        log.info("Пользователь с email: {} успешно добавлен!", user.getEmail());
        return user;
    }

    public User updateUser(User user) {
        log.info("Получен зарпос /PUT на обновление пользователя");
        if (user.getEmail().isEmpty() || user.getEmail().isBlank()) {
            log.warn("Ошибка! Введен некоректный email");
            throw new InvalidEmailException("Неверный e-mail!");
        }
        for (User user1 : users) {
            if (user.equals(user1)) {
                users.set(users.indexOf(user1), user);
                log.info("Пользователь с email: {} успешно обновлен", user.getEmail());
                return user;
            }
        }
        log.info("Пользователь с email: {} успешно создан!", user.getEmail());
        users.add(user);
        return user;
    }

    public User findUserByEmail(String email){
        for (User user : users){
            if (user.getEmail().equals(email)){
                return user;
            }
        }
        return null;
    }
}
