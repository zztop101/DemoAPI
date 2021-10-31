package com.example.demo.repository;

import com.example.demo.model.Comment;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Repository
public class DefaultBlogRepository implements BlogRepository {

    @Override
    public Flux<User> users() {
        return WebClient
                .create()
                .get()
                .uri("https://jsonplaceholder.typicode.com/users")
                .retrieve()
                .bodyToFlux(User.class);
    }

    @Override
    public Flux<Post> posts() {
        return WebClient
                .create()
                .get()
                .uri("https://jsonplaceholder.typicode.com/posts")
                .retrieve()
                .bodyToFlux(Post.class);
    }

    @Override
    public Flux<Comment> comments() {
        return WebClient
                .create()
                .get()
                .uri("https://jsonplaceholder.typicode.com/comments")
                .retrieve()
                .bodyToFlux(Comment.class);
    }
}
