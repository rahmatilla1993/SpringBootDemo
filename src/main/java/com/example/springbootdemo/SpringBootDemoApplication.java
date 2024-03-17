package com.example.springbootdemo;

import com.example.springbootdemo.properties.PersonPropertiesWithConf;
import com.example.springbootdemo.yaml.YamlConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableConfigurationProperties({
        PersonPropertiesWithConf.class,
        YamlConfig.class
})
@EnableAsync
public class SpringBootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }
}
