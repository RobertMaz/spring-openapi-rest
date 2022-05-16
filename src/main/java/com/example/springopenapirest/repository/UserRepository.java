package com.example.springopenapirest.repository;

import com.example.springopenapirest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}