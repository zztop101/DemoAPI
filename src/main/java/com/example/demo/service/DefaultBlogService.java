package com.example.demo.service;

import com.example.demo.model.Comment;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.model.UserAndPost;
import com.example.demo.repository.BlogRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultBlogService implements BlogService {

    private BlogRepository blogRepository;

    public DefaultBlogService(final BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public Flux<UserAndPost> userAndPosts() {

        Flux<User> users = blogRepository.users();
        Flux<Post> posts = blogRepository.posts();

        return Flux.zip(users.collectList(), posts.collectList(), (usersArg, postsArg) -> usersArg.stream().map(
                user -> new UserAndPost(user, extractPostsByUser(postsArg, user.getId())))
           ).flatMap(Flux::fromStream);

    }

    public Flux<Comment> comments(long postId) {
        return blogRepository.comments().filter(c -> c.getPostId() == postId);
    }

    private List<Post> extractPostsByUser(List<Post> posts, long userId) {
        return posts.stream()
                .filter(p -> p.getUserId() == userId)
                .collect(Collectors.toList());
    }
}
