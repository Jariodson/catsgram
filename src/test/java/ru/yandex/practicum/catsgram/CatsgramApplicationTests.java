package ru.yandex.practicum.catsgram;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.catsgram.controller.PostController;
import ru.yandex.practicum.catsgram.controller.UserController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CatsgramApplicationTests {
	@Autowired
	PostController postController;

	@Autowired
	UserController userController;

	@Test
	void contextLoads() {
		assertThat(postController).isNotNull();
		assertThat(userController).isNotNull();
	}

}
