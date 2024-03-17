package com.example.springbootdemo.mappers.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class StudentDto {

    private String fullName;
    private int age;
    private String addressCity;
    private int addressHomeNumber;
}
