package com.example.springbootdemo.mappers.employee;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Employee {

    private String id;
    private String firstName;
    private String lastName;
}
