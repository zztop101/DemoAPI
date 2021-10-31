package com.example.demo.repository;

import com.example.demo.model.Comment;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import reactor.core.publisher.Flux;

public interface BlogRepository {

    Flux<User> users();
    Flux<Post> posts();
    Flux<Comment> comments();

}
