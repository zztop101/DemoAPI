package com.example.demo.controller;

import com.example.demo.model.Comment;
import com.example.demo.model.UserAndPost;
import com.example.demo.repository.BlogRepository;
import com.example.demo.repository.DefaultBlogRepository;
import com.example.demo.service.BlogService;
import com.example.demo.service.DefaultBlogService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = BlogController.class)
@Import({DefaultBlogService.class, DefaultBlogRepository.class})
public class BlogControllerIT {

    @Autowired
    WebTestClient webTestClient;

    @Test
    public void testGetUsersAndPosts() {

        EntityExchangeResult<List<UserAndPost>> result = webTestClient.get()
                .uri("/api/users-and-posts")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(UserAndPost.class)
                .value(userAndPosts -> userAndPosts.get(0).getUser().getName(), equalTo("Leanne Graham"))
                .value(userAndPosts -> userAndPosts.get(1).getUser().getName(), equalTo("Ervin Howell"))
                .returnResult();

        System.out.println(result);

    }

    @Test
    public void testGetComments() {

        EntityExchangeResult<List<Comment>> result = webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/api/comments")
                                .queryParam("postId", "1")
                                .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Comment.class)
                .hasSize(5)
                .returnResult();

        System.out.println(result);

    }
}
