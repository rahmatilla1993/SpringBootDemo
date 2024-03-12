package com.example.springbootdemo.repository;

import com.example.springbootdemo.entity.Role;
import com.example.springbootdemo.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRoleName(RoleName roleName);
}
