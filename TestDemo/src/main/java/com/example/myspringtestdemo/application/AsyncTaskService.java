package com.example.myspringtestdemo.application;

import com.example.myspringtestdemo.application.dto.UserWithPostDto;
import com.example.myspringtestdemo.application.dto.mapper.EntityToDto;
import com.example.myspringtestdemo.domain.Post;
import com.example.myspringtestdemo.domain.PostRepository;
import com.example.myspringtestdemo.domain.User;
import com.example.myspringtestdemo.domain.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.List;

import static java.lang.Thread.sleep;
@Slf4j
@Service
public class AsyncTaskService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Autowired
    public AsyncTaskService(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Async
    public void executeAsyncTask(int i) {
        System.out.println("线程" + Thread.currentThread().getName() + " 执行异步任务：" + i);
    }

    @Async
    public void saveUsers(List<User> users) throws InterruptedException {

        System.out.println("线程1" + Thread.currentThread().getName() + " 执行异步任务：");

        userRepository.saveUsers(users);
    }

    @Async
    public void savePosts(List<Post> posts) {
        System.out.println("线程2" + Thread.currentThread().getName() + " 执行异步任务：");
        postRepository.savePosts(posts);
    }


    public UserWithPostDto getAllInfoById(String userId) {
        User findUser = userRepository.findById(userId);
        List<Post> findPosts = postRepository.findByUserId(userId);
        UserWithPostDto userWithPostDto = EntityToDto.INSTANCE.getClone(findUser);
        userWithPostDto.setPosts(findPosts);
        return userWithPostDto;
    }
    public UserRepository getAllInfoByIdWithReactor3(){
        return null;
    }

    public void test(){
        StepVerifier.create(
                        Flux.just("flux", "mono")
                                .flatMap(s -> Flux.fromArray(s.split("\\s*"))   // 1
                                        .delayElements(Duration.ofMillis(100))) // 2
                                .doOnNext(log::debug)) // 3
                .expectNextCount(8) // 4
                .verifyComplete();
    }
}
