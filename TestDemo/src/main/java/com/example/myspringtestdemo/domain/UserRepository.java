package com.example.myspringtestdemo.domain;

import com.example.myspringtestdemo.application.dto.UserDto;
import reactor.core.publisher.Mono;

import java.util.List;

public interface UserRepository {
    User findById(String id);

    UserWithPost findUserPostById(String id);

    void saveUsers(List<User> users);

    void saveUsersAsync(List<User> collect);

    Mono<UserDto> findById2(String userId);
}
