package com.example.springbootdemo.mappers.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AddressDto {
    private String city;
    private int homeNumber;
}
