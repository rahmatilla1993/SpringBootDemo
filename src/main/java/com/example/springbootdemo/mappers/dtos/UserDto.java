package com.example.springbootdemo.mappers.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserDto {
    private String d_fullName;
    private int d_age;
}
