package com.example.springbootdemo.mappers.car;

import org.junit.jupiter.api.Test;

import static com.example.springbootdemo.mappers.car.CarMapper.CAR_MAPPER;
import static org.junit.jupiter.api.Assertions.*;

class CarMapperTest {

    @Test
    void fromCar() {
        Car car = Car.builder()
                .model("BMW")
                .maxSpeed(200)
                .build();
        CarDto carDto = CAR_MAPPER.fromCar(car);
        System.out.println("carDto = " + carDto);
    }

    @Test
    void toCar() {
        CarDto dto = CarDto.builder()
                .model("Mercedes")
                .maxSpeed(230)
                .build();
        Car car = CAR_MAPPER.toCar(dto);
        System.out.println("car = " + car);
    }
}