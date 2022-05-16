package com.example.springopenapirest.service;

import com.example.springopenapirest.entity.User;
import com.example.web.dto.FindUsersRequest;
import com.example.web.dto.UserRequest;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(UserRequest userRequest);

    List<User> findUsers(FindUsersRequest request);

    Optional<User> getUserById(Long id);
}
