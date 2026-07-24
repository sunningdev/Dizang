package com.dizang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dizang.mapper")
public class DizangApplication {
    public static void main(String[] args) {
        SpringApplication.run(DizangApplication.class, args);
    }
}