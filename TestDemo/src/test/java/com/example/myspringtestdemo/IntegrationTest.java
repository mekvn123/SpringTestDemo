package com.example.myspringtestdemo;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;


@MybatisTest
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MySpringTestDemoApplication.class, webEnvironment = RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional //支持事物，@SpringBootTest 事物默认自动回滚
public class IntegrationTest {
    private static final String UTF_8 = "UTF-8";

    @Autowired
    protected WebApplicationContext context;

    protected MockMvcRequestSpecification given() {
        RestAssuredMockMvc.webAppContextSetup(context);
        return RestAssuredMockMvc
                .given()
                .header("Accept", ContentType.JSON.withCharset(UTF_8))
                .header("Content-Type", ContentType.JSON.withCharset(UTF_8));
    }
}
