package com.example.myspringtestdemo.application.dto.mapper;

import com.example.myspringtestdemo.application.dto.UserDto;
import com.example.myspringtestdemo.application.dto.UserWithPostDto;
import com.example.myspringtestdemo.domain.User;
import com.example.myspringtestdemo.domain.UserWithPost;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EntityToDto {
    EntityToDto INSTANCE = Mappers.getMapper(EntityToDto.class);

    UserDto clone(User user);

    UserWithPostDto clone(UserWithPost userWithPost);

    UserWithPostDto getClone(User user);
}
