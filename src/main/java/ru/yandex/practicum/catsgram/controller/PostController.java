package ru.yandex.practicum.catsgram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostService;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Optional;

@RestController
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(value = {"/posts"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Post> findAll(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                    @NotNull
                                    @RequestParam(value = "size", defaultValue = "10", required = false) Integer size,
                                    @RequestParam(value = "sort", defaultValue = "desc", required = false) String sort
    ) {
        int from = page * size;
        return postService.findAll(size, sort, from);
    }

    @GetMapping(value = {"/posts/{postId}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Post> findById(@PathVariable(required = false) String postId) {
        if (postId != null) {
            return postService.findById(Integer.parseInt(postId));
        }
        return Optional.empty();
    }

    @PostMapping(value = "/posts", produces = MediaType.APPLICATION_JSON_VALUE)
    public Post create(@RequestBody Post post) {
        return postService.create(post);
    }
}