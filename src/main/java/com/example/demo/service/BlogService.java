package com.example.demo.service;

import com.example.demo.model.Comment;
import com.example.demo.model.UserAndPost;
import reactor.core.publisher.Flux;

public interface BlogService {

   Flux<UserAndPost> userAndPosts();
   Flux<Comment> comments(long postId);
}
