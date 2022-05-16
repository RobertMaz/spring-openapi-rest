package com.example.springopenapirest.web;

import com.example.springopenapirest.entity.User;
import com.example.springopenapirest.mapper.UserMapper;
import com.example.springopenapirest.service.UserService;
import com.example.web.controller.UserApi;
import com.example.web.dto.FindUsersRequest;
import com.example.web.dto.UserRequest;
import com.example.web.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;

    @Override
    public ResponseEntity<UserResponse> createUser(UserRequest userRequest) {
        User user = userService.createUser(userRequest);
        UserResponse response = UserMapper.INSTANCE.toDto(user);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<UserResponse>> findUsers(FindUsersRequest request) {
        List<User> users = userService.findUsers(request);
        List<UserResponse> userResponses = UserMapper.INSTANCE.toDto(users);
        return ResponseEntity.ok(userResponses);
    }

    @Override
    public ResponseEntity<UserResponse> getUserById(Long id) {
        User user = userService.getUserById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with id %s not found", id)));
        UserResponse userRes = UserMapper.INSTANCE.toDto(user);
        return ResponseEntity.ok(userRes);
    }
}
