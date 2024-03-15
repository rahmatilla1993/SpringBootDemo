package com.example.springbootdemo.entity;

import com.example.springbootdemo.enums.RoleName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Schema(name = "Auth Role", description = "Foydalanuvchi roli")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    @Parameter(name = "Role nomi", description = "Role nomlari")
    private RoleName roleName;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<AuthUser> authUsers;
}
