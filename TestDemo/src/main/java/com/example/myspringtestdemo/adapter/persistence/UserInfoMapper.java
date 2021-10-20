package com.example.myspringtestdemo.adapter.persistence;

import com.example.myspringtestdemo.domain.User;
import com.example.myspringtestdemo.domain.UserWithPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.logging.Log;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserInfoMapper {
    Optional<User> findUserById(@Param("userId") String id);

    //@Async
     void save(@Param("param") User user);

    Optional<UserWithPost> findUserPostById(@Param("userId")String id);

    void saveUsers(@Param("list") List<User> users);

}




