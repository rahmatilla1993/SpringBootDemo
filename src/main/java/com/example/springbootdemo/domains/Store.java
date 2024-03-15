package com.example.springbootdemo.domains;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Store entity")
@Builder
public class Store {
    private long id;
    private String title;
    private String createdBy;
    private int number;
    private String desc;
}
