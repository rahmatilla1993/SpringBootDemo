package com.example.springbootdemo.mappers.employee;

import org.junit.jupiter.api.Test;

import static com.example.springbootdemo.mappers.employee.EmployeeMapper.EMPLOYEE_MAPPER;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeMapperTest {

    @Test
    void fromDto() {
        EmployeeDto dto = EmployeeDto.builder()
                .empFirstName("John")
                .empLastName("Smith")
                .build();
        Employee employee = EMPLOYEE_MAPPER.fromDto(dto);
        System.out.println("employee = " + employee);
    }

    @Test
    void toDto() {
        Employee employee = Employee.builder()
                .firstName("Kyle")
                .lastName("Evans")
                .build();
        EmployeeDto employeeDto = EMPLOYEE_MAPPER.toDto(employee);
        System.out.println("employeeDto = " + employeeDto);
    }
}