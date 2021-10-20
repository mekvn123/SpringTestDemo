package com.example.myspringtestdemo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Post {
    String postId;
    String comment;
    String userId;
}
