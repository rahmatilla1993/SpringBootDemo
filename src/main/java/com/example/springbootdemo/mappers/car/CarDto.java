package com.example.springbootdemo.mappers.car;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CarDto {
    private String model;
    private Integer maxSpeed;
}
