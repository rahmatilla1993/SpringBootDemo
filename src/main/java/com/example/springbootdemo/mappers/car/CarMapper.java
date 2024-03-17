package com.example.springbootdemo.mappers.car;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper
public interface CarMapper {

    CarMapper CAR_MAPPER = Mappers.getMapper(CarMapper.class);

    CarDto fromCar(Car car);

    @Mapping(target = "id", expression = "java(genID())")
    Car toCar(CarDto dto);

    default String genID() {
        return UUID.randomUUID().toString();
    }
}
