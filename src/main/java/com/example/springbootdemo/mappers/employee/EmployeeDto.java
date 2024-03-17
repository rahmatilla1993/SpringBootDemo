package com.example.springbootdemo.mappers.employee;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class EmployeeDto {
    private String empFirstName;
    private String empLastName;
}
