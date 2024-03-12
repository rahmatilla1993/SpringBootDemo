package com.example.springbootdemo;

import com.example.springbootdemo.entity.AuthUser;
import com.example.springbootdemo.entity.Role;
import com.example.springbootdemo.enums.RoleName;
import com.example.springbootdemo.repository.AuthUserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SpringBootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }

//    @Bean
    public ApplicationRunner runner(AuthUserRepository authUserRepository) {
        return (args -> {
            List<Role> roles = List.of(
                    Role.builder().roleName(RoleName.ROLE_USER).build(),
                    Role.builder().roleName(RoleName.ROLE_ADMIN).build()
            );
            AuthUser authUser = AuthUser.builder()
                    .username("user")
                    .password("$2a$12$Aa4dofbJgvE48UIVDOcWWOz6yKyXm1M4PyB/jQmWSKUG9BbAMJcJa")
                    .build();
            authUser.setRoles(roles);
            authUserRepository.save(authUser);
        });
    }
}
