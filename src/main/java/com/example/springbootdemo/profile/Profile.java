package com.example.springbootdemo.profile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profile {

    private String name;
    private String environment;
    private boolean enabled;
    private List<String> servers;
}
