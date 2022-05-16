package com.example.springopenapirest.service;

import com.example.springopenapirest.entity.User;
import com.example.springopenapirest.mapper.UserMapper;
import com.example.springopenapirest.repository.UserRepository;
import com.example.springopenapirest.repository.specification.UserSpec;
import com.example.web.dto.FindUsersRequest;
import com.example.web.dto.UserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(UserRequest userRequest) {
        User user = UserMapper.INSTANCE.toUser(userRequest);
        userRepository.save(user);
        return user;
    }

    @Override
    public List<User> findUsers(FindUsersRequest request) {
        UserSpec spec = new UserSpec();
        spec.setName(request.getName());
        spec.setPhone(request.getPhone());
        return userRepository.findAll(spec);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
}
