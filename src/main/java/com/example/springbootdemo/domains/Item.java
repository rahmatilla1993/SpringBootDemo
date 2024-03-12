package com.example.springbootdemo.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    private long id;
    private String title;
    private String desc;
    private double price;
}
