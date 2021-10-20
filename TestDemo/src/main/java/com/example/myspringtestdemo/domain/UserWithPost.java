package com.example.myspringtestdemo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
public class UserWithPost extends User {
    @Getter
    @Setter
    private List<Post> posts;

    public UserWithPost(final String userId, final String userName, final String userPassword, final String userEmail,
                        final List<Post> posts) {
        super(userId, userName, userPassword, userEmail);
        this.setPosts(posts);
    }
}
