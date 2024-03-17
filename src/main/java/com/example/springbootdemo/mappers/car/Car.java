package com.example.springbootdemo.mappers.car;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Car {

    private String id;
    private String model;
    private Integer maxSpeed;
}
