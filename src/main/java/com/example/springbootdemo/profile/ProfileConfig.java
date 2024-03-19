package com.example.springbootdemo.profile;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(value = "spring.config")
@Getter
@Setter
public class ProfileConfig {
    private Profile activate;
}
