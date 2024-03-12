package com.example.springbootdemo.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Store {
    private long id;
    private String title;
    private String createdBy;
    private int number;
    private String desc;
}
