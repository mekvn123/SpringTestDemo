package com.example.myspringtestdemo.domain;

import java.util.List;

public interface PostRepository {
    List<Post> findByUserId(String id);
    void savePosts(List<Post> posts);
}
