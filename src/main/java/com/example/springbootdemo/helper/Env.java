package com.example.springbootdemo.helper;

import lombok.*;

import java.util.List;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Env {
    private List<String> env;
}
