package com.example.springbootdemo.dto;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ParameterObject
public class StoreDto {
    @NotBlank(message = "Title bo'sh qolmasin")
    @Parameter(name = "title", required = true)
    private String title;
    @Parameter(name = "number", description = "Store nomeri", required = true)
    @Min(value = 1, message = "Qiymat manfiy bo'lmasin")
    private int number;
    @Parameter(name = "description", description = "Store haqida description")
    private String desc;
}
