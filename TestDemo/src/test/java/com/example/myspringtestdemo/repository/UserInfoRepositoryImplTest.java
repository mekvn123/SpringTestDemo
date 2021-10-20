package com.example.myspringtestdemo.repository;

import com.example.myspringtestdemo.unit.JsonEquals;
import com.example.myspringtestdemo.unit.JsonHelper;
import com.example.myspringtestdemo.UnitTest;
import com.example.myspringtestdemo.adapter.persistence.UserInfoMapper;
import com.example.myspringtestdemo.adapter.persistence.UserRepositoryImpl;
import com.example.myspringtestdemo.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.doReturn;

public class UserInfoRepositoryImplTest extends UnitTest {
    private static final BinaryOperator<List<User>> addList =
            (param_a, param_b) -> Stream.concat(param_a.stream(), param_b.stream()).collect(Collectors.toList());
    @Mock
    UserInfoMapper userInfoMapper;

    @InjectMocks
    private UserRepositoryImpl userRepository;

    @Test
    public void should_get_user_info_success() {
        //given
        String jsonPath = "json/should_get_user_info_success.json";
        User testUser = User.builder()
                .userId("ID00001")
                .userName("test_user_1")
                .userPassword("test_user_password")
                .userEmail("test_user@test.com").build();
        doReturn(Optional.of(testUser)).when(this.userInfoMapper).findUserById("ID00001");

        // when
        User findUser = userRepository.findById("ID00001");

        // then
        Assertions.assertTrue(JsonEquals.equalToJsonFile(jsonPath,JsonHelper.serializable(findUser)));
    }
}
