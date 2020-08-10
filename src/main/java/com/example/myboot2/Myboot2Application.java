package com.example.myboot2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.example.myboot2.mapper")
@EnableTransactionManagement
public class Myboot2Application {

    public static void main(String[] args) {
        SpringApplication.run(Myboot2Application.class, args);
    }

}
