package com.example.springbootdemo.repository;

import com.example.springbootdemo.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, String> {
    Optional<AuthUser> findByUsername(String username);
}
