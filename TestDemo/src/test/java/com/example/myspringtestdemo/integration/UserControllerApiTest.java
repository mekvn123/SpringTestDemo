package com.example.myspringtestdemo.integration;


import com.example.myspringtestdemo.IntegrationTest;
import com.example.myspringtestdemo.adapter.persistence.UserInfoMapper;
import com.example.myspringtestdemo.domain.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import static org.hamcrest.Matchers.is;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserControllerApiTest extends IntegrationTest {
    @Autowired
    private ApplicationContext applicationContext;

    private UserInfoMapper userInfoMapper;

    @BeforeAll
    public void setUp() {
        userInfoMapper = applicationContext.getBean(UserInfoMapper.class);
    }

    @Test
    public void should_get_user_success_by_userId() {
        // given
        User testUser = User.builder()
                .userId("ID00001")
                .userName("test_user_1")
                .userPassword("test_user_password")
                .userEmail("test_user@test.com").build();
        userInfoMapper.save(testUser);

        //when
        given()
                .when()
                .get(String.format("/test_demo/%s", testUser.getUserId()))
                .then()
                .statusCode(200)
                .body("userName", is("test_user_1"));
    }

    @Test
    void should_get_user_by_id_with_mono() {
        // given
        User testUser = User.builder()
                .userId("ID00001")
                .userName("test_user_1")
                .userPassword("test_user_password")
                .userEmail("test_user@test.com").build();
        userInfoMapper.save(testUser);

        // when
        given()
                .when()
                .get(String.format("/test_demo/mono/%s", testUser.getUserId()))
                .then()
                .statusCode(200);
    }
    //for
}

