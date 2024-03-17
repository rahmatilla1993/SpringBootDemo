package com.example.springbootdemo.yaml;

import com.example.springbootdemo.helper.Env;
import com.example.springbootdemo.helper.Person;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@Setter
@ConfigurationProperties(prefix = "example")
public class YamlConfig {
    private Person person;
    private Env config;
}
