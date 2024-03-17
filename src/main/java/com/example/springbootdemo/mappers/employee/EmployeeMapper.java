package com.example.springbootdemo.mappers.employee;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper EMPLOYEE_MAPPER = Mappers.getMapper(EmployeeMapper.class);

    @Mappings({
            @Mapping(target = "firstName", source = "empFirstName"),
            @Mapping(target = "lastName", source = "empLastName"),
            @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID().toString())")
    })
    Employee fromDto(EmployeeDto dto);

    @InheritInverseConfiguration
    EmployeeDto toDto(Employee employee);
}
