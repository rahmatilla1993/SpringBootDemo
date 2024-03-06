package com.example.springbootdemo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "mailing")
@Data
public class MailConfig {

    private String server;
    private String user;
    private String password;
    private boolean enableTls;
}
