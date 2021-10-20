package com.example.myspringtestdemo.repository.mapper;

import com.example.myspringtestdemo.adapter.persistence.PostRepositoryImpl;
import com.example.myspringtestdemo.adapter.persistence.UserInfoMapper;
import com.example.myspringtestdemo.adapter.persistence.UserRepositoryImpl;
import com.example.myspringtestdemo.application.AsyncTaskService;
import com.example.myspringtestdemo.domain.Post;
import com.example.myspringtestdemo.domain.User;
import com.example.myspringtestdemo.IntegrationTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@MybatisTest
public class MapperTest extends IntegrationTest {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserRepositoryImpl userRepository;
    @Autowired
    private PostRepositoryImpl postRepository;
    @Autowired
    private AsyncTaskService asyncTaskService;

    private static final List<User> users = newArrayList();
    private static final List<Post> posts = newArrayList();

    @BeforeAll
    private static void setAll() {
        for (int i = 0; i < 1000; i++) {
            users.add(User.builder()
                    .userId("XD".concat(String.valueOf(i)))
                    .userName("test".concat(String.valueOf(i)))
                    .userEmail("test@email.com")
                    .userPassword("password")
                    .build());
            posts.add(new Post("PD".concat(String.valueOf(i)), "test", "XD".concat(String.valueOf(i % 10))));
        }

    }

    @Test
    public void should_add_success() {
        long startTime = System.currentTimeMillis();
        userRepository.saveUsersAsync(users);
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间： " + (endTime - startTime) + "ms");
    }


    @Test
    public void should_success_insert_by_save() {
        long startTime = System.currentTimeMillis();
        userRepository.saveUsers(users);
        postRepository.savePosts(posts);
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间： " + (endTime - startTime) + "ms");
    }

    @Test
    public void should_save_both() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        long startTime = System.currentTimeMillis();
        //executor.execute(()->userRepository.saveUsers(users));
        try {
            asyncTaskService.savePosts(posts);
            asyncTaskService.saveUsers(users);

        } finally {
            executor.shutdown();
        }
        //executor.execute(()->postRepository.savePosts(posts));
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间： " + (endTime - startTime) + "ms");

    }
}
