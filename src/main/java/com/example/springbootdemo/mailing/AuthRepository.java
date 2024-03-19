package com.example.springbootdemo.mailing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<AuthUser, Integer> {

    Optional<AuthUser> findByUsername(String username);
}
