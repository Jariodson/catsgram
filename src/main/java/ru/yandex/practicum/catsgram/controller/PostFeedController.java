package ru.yandex.practicum.catsgram.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.catsgram.model.Post;

import java.util.List;

@RestController("/feed")
public class PostFeedController {
    @Autowired
    ObjectMapper objectMapper = new ObjectMapper();
    @PostMapping(value = "friends", params = MediaType.APPLICATION_JSON_VALUE)
    public List<Post> postLastFriendsPosts(@RequestBody String post) throws Exception {
    }
}
