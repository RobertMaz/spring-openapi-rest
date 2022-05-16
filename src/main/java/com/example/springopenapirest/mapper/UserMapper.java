package com.example.springopenapirest.mapper;

import com.example.springopenapirest.entity.User;
import com.example.web.dto.UserRequest;
import com.example.web.dto.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = UserMapper.class)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponse toDto(User user);

    List<UserResponse> toDto(List<User> users);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", expression = "java(userRequest.getFirstName() + \" \" + userRequest.getLastName())")
    User toUser(UserRequest userRequest);
}
