package com.example.springbootdemo.entity;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Schema(name = "AuthUser", description = "AuthUser entity haqida")
public class AuthUser {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Parameter(name = "id", description = "AuthUser entity idsi")
    private String id;

    @Column(unique = true, nullable = false)
    @Parameter(name = "id", description = "AuthUser entity usernyemi")
    private String username;

    @Column(nullable = false)
    @Parameter(name = "id", description = "AuthUser entity paroli")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    @Cascade({CascadeType.PERSIST})
    private List<Role> roles;
}
