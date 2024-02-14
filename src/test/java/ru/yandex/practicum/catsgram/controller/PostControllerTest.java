package ru.yandex.practicum.catsgram.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostService;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class PostControllerTest {
    @Autowired
    private static ObjectMapper objectMapper;
    private MockMvc mockMvc;
    @Mock
    private PostService postService;

    @InjectMocks
    private PostController postController;

    @BeforeEach
    void beforeAll() {
        objectMapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
        mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
    }

    //
    //        Post post2 = Post.builder()
    //                .id(1)
    //                .author("galyan@yandex.ru")
    //                .description("description2")
    //                .photoUrl("photo-url1")
    //                .build();

    @Test
    void testFindAll() throws Exception {
        Post post1 = Post.builder()
                .id(0)
                .author("semenov@yandex.ru")
                .description("description1")
                .photoUrl("photo-url1")
                .build();
        when(postService.findAll(10, "desc", 0)).thenReturn(List.of(post1));
        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void findById() {
    }

    @Test
    void create() {
    }
}