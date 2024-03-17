package com.example.springbootdemo.mappers.dtos;

import org.junit.jupiter.api.Test;

import static com.example.springbootdemo.mappers.dtos.DtoMapper.DTO_MAPPER;
import static org.junit.jupiter.api.Assertions.*;

class DtoMapperTest {

    @Test
    void createDto() {
        UserDto userDto = UserDto.builder()
                .d_fullName("Vali Valiyev")
                .d_age(22)
                .build();
        AddressDto addressDto = AddressDto.builder()
                .city("London")
                .homeNumber(22)
                .build();
        StudentDto dto = DTO_MAPPER.createDto(userDto, addressDto);
        System.out.println("dto = " + dto);
    }
}