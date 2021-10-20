package com.example.myspringtestdemo.controller;

import com.example.myspringtestdemo.UnitTest;
import com.example.myspringtestdemo.application.UserService;
import com.example.myspringtestdemo.application.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest extends UnitTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void should_get_user_success_by_userId() throws Exception {
        // given
        UserDto userDto = UserDto.builder()
                .userId("ID00001")
                .userName("test_user_1")
                .userPassword("test_user_password")
                .userEmail("test_user@test.com").build();
        when(userService.getUser(any())).thenReturn(userDto);

        // when
        mvc.perform(get(String.format("/test_demo/%s", userDto.getUserId())).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value(userDto.getUserName()))
                .andExpect(jsonPath("$.userPassword").value(userDto.getUserPassword()))
                .andExpect(jsonPath("$.userEmail").value(userDto.getUserEmail()));
    }
}
