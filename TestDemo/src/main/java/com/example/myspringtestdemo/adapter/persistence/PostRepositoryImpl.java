package com.example.myspringtestdemo.adapter.persistence;

import com.example.myspringtestdemo.domain.Post;
import com.example.myspringtestdemo.domain.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {
    private final PostMapper postMapping;
    @Resource
    private RedisTemplate<String,Post> redisTemplate;

    @Override
    public List<Post> findByUserId(String id) {
        return postMapping.findByUserId(id);
    }

    public void savePosts(List<Post> posts) {
        postMapping.savePosts(posts);
    }
}
