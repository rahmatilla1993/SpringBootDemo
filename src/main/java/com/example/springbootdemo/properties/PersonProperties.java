package com.example.springbootdemo.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
@Setter
public class PersonProperties {
    @Value("${ex.person.id}")
    private Long id;
    @Value("${ex.person.name}")
    private String name;
    @Value("${ex.person.age}")
    private Integer age;
}
