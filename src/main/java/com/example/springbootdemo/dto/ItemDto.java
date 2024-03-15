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
public class ItemDto {
    @Parameter(name = "title", description = "Item title i", required = true)
    @NotBlank(message = "Title bo'sh qolmasin")
    private String title;
    @Parameter(name = "Description", description = "Item haqida")
    private String desc;
    @Parameter(name = "price", description = "Item narxi", required = true)
    @Min(value = 1, message = "Narx manfiy son bo'la olmaydi")
    private double price;
}
