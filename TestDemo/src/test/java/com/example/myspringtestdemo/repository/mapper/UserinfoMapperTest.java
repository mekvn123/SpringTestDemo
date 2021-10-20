package com.example.myspringtestdemo.repository.mapper;

import com.example.myspringtestdemo.IntegrationTest;
import com.example.myspringtestdemo.adapter.persistence.UserInfoMapper;
import com.example.myspringtestdemo.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserinfoMapperTest extends IntegrationTest {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Test
    @DisplayName("get user by id success when user exist")
    void should_get_user_success_by_id(){
        // given
        User testUser = User.builder()
                .userId("ID00002")
                .userName("test_user_2")
                .userPassword("test_user_password")
                .userEmail("test_user@test.com").build();
        userInfoMapper.save(testUser);

        //when
        Optional<User> findUser = userInfoMapper.findUserById("ID00002");

        Assertions.assertTrue(findUser.isPresent());
    }
}
