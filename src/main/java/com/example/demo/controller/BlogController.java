package com.example.demo.controller;

import com.example.demo.model.Comment;
import com.example.demo.model.UserAndPost;
import com.example.demo.service.BlogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class BlogController {

    private BlogService blogService;

    public BlogController(final BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/api/users-and-posts")
    public Flux<UserAndPost> fetchUsersAndPosts() {
        return blogService.userAndPosts();
    }

    @GetMapping("/api/comments")
    public Flux<Comment> fetchComments(@RequestParam(name = "postId") String postId) {
        return blogService.comments(Long.valueOf(postId));
    }


}
