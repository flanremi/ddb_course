package com.ddbs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ddbs.mapper")
public class DDBSPreApplication {

    public static void main(String[] args) {
        SpringApplication.run(DDBSPreApplication.class, args);
    }

}
