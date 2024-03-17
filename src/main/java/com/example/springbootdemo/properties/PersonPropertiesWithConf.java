package com.example.springbootdemo.properties;

import com.example.springbootdemo.helper.Person;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component("personPropConf")
@ToString
@Getter
@Setter
@ConfigurationProperties(prefix = "ex")
public class PersonPropertiesWithConf {
    private Person person;
}
