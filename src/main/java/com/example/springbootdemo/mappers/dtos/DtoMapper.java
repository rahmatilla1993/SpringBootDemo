package com.example.springbootdemo.mappers.dtos;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DtoMapper {

    DtoMapper DTO_MAPPER = Mappers.getMapper(DtoMapper.class);

    @Mappings({
            @Mapping(target = "fullName", source = "userDto.d_fullName"),
            @Mapping(target = "age", source = "userDto.d_age"),
            @Mapping(target = "addressCity", source = "addressDto.city"),
            @Mapping(target = "addressHomeNumber", source = "addressDto.homeNumber"),
    })
    StudentDto createDto(UserDto userDto, AddressDto addressDto);
}
