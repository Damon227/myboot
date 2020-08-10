package com.example.myboot2.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@PropertySource(value = "classpath:custom.properties", encoding = "utf-8")
@ConfigurationProperties("def.country")
@Data
public class Country {
    private List<String> cities;
}
