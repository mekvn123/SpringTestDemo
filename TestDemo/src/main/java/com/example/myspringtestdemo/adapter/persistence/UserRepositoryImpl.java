package com.example.myspringtestdemo.adapter.persistence;

import com.example.myspringtestdemo.application.dto.UserDto;
import com.example.myspringtestdemo.application.dto.mapper.EntityToDto;
import com.example.myspringtestdemo.domain.User;
import com.example.myspringtestdemo.domain.UserRepository;
import com.example.myspringtestdemo.domain.UserWithPost;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepositoryImpl implements UserRepository {
    private static final BiConsumer<User, RedisTemplate<String, User>> SAVE_USER = (user, redisTemplate) -> {
        redisTemplate.opsForValue().set(user.getUserId(), user, LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")), TimeUnit.MICROSECONDS);
    };

    private static final int POOL_SIZE = 10;
    private final UserInfoMapper userMapper;
    @Resource
    private RedisTemplate<String, User> redisTemplate;

    @Override
    public User findById(String id) {
        User findUser = redisTemplate.opsForValue().get(id);
        if (Objects.isNull(findUser)) {
            findUser = userMapper.findUserById(id).orElseThrow(() -> new RuntimeException("user is not exist"));
            redisTemplate.opsForValue().set(id, findUser, LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")), TimeUnit.MICROSECONDS);
        }
        return findUser;
    }

    @Override
    public UserWithPost findUserPostById(String id) {
        return userMapper.findUserPostById(id).orElse(new UserWithPost());
    }

    @Override
    public void saveUsers(List<User> users) {
        users.stream().forEach(user -> {
            if (Objects.nonNull(redisTemplate.opsForValue().get(user.getUserId()))) {
                SAVE_USER.accept(user, redisTemplate);
            }
        });
        userMapper.saveUsers(users);
    }

    @Override
    public void saveUsersAsync(List<User> users) {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        CountDownLatch countDownLatch = new CountDownLatch(users.size());
        int i = 0;
        //出现异常返回
        try {
            for (; i < users.size(); i++) {
                int index = i;
                executor.execute(() -> {
                    try {
                        userMapper.save(users.get(index));
                    } catch (Exception e) {
                        log.error("第" + index + "条出错  " + e.getMessage());
                        throw new RuntimeException("第" + index + "条出错  ");
                    } finally {
                        //计数器减一
                        countDownLatch.countDown();
                    }
                });
            }
            //如果计数器不为0，就等待，这个如果计数不正确，主线程就会先执行，相当于数据还没有插完，就返回成功给前端.
            countDownLatch.await();
        } catch (Exception ignored) {

        } finally {
            //关闭线程池
            executor.shutdown();
        }
    }

    @Override
    public Mono<UserDto> findById2(String userId) {
        return Mono.just(EntityToDto.INSTANCE.clone(userMapper.findUserById(userId).orElse(new User())));
    }
}

