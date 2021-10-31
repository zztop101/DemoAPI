package com.example.demo.repository;

import com.example.demo.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@DisplayName(("blog repository tests"))
public class BlogRepositoryTest {

    private BlogRepository blogRepository;

    @BeforeEach
    void beforeEach() {
        blogRepository = new DefaultBlogRepository();
    }

    @DisplayName("Test successful retrieve users")
    @Test
    public void testSuccessfulRetrieveUsers() {
        final Flux<User> result = blogRepository.users();
        StepVerifier
                .create(result)
                .expectNextMatches(u -> u.getUsername().equals("Bret"))
                .expectNextCount(9)
                .verifyComplete();
    }
}
