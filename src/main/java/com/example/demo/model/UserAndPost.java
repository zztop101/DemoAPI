package com.example.demo.model;

import java.util.List;

public class UserAndPost {

    private User user;
    private List<Post> posts;

    public UserAndPost(User user, List<Post> posts) {
        this.user = user;
        this.posts = posts;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }


}
