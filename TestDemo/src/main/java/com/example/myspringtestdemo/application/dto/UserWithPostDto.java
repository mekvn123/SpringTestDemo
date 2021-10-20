package com.example.myspringtestdemo.application.dto;

import com.example.myspringtestdemo.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserWithPostDto {
    private String userId;
    private String userName;
    private String userPassword;
    private String userEmail;
    private List<Post> posts;
}
