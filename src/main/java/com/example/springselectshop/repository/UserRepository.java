package com.example.springselectshop.repository;

import com.example.springselectshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByKakaoId(Long id);
    Optional<User> findByEmail(String email);
}