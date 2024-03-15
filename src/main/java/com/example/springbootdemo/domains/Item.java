package com.example.springbootdemo.domains;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Item Entity")
@Builder
public class Item {
    private long id;
    private String title;
    private String desc;
    private double price;
}
