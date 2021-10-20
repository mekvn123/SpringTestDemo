package com.example.myspringtestdemo.adapter.persistence;

import com.example.myspringtestdemo.domain.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {
    List<Post> findByUserId(@Param("userId") String id);

    void savePosts(@Param("list") List<Post> posts);
}
