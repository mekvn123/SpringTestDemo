package com.example.myspringtestdemo.service;

import com.example.myspringtestdemo.UnitTest;
import com.example.myspringtestdemo.adapter.persistence.UserRepositoryImpl;
import com.example.myspringtestdemo.application.UserService;
import com.example.myspringtestdemo.application.dto.UserDto;
import com.example.myspringtestdemo.application.dto.mapper.DtoToEntity;
import com.example.myspringtestdemo.domain.User;
import com.example.myspringtestdemo.unit.JsonEquals;
import com.example.myspringtestdemo.unit.JsonHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


public class UserServiceTest extends UnitTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepositoryImpl userRepository;

    @Test
    public void should_get_User_success_if_user_exist() {
        // given
        String jsonPath = "json/should_get_user_info_success.json";
        User testUser = User.builder()
                .userId("ID00001")
                .userName("test_user_1")
                .userPassword("test_user_password")
                .userEmail("test_user@test.com").build();
        when(userRepository.findById(any())).thenReturn(testUser);

        // when
        UserDto findUser = userService.getUser("ID00001");

        // then
        Assertions.assertTrue(JsonEquals.equalToJsonFile(jsonPath, JsonHelper.serializable(DtoToEntity.INSTANCE.clone(findUser))));
    }
}
