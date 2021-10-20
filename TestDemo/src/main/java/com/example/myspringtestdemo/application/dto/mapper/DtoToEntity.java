package com.example.myspringtestdemo.application.dto.mapper;

import com.example.myspringtestdemo.application.dto.UserDto;
import com.example.myspringtestdemo.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DtoToEntity {
    DtoToEntity INSTANCE = Mappers.getMapper(DtoToEntity.class);

    User clone(UserDto userDto);
}
