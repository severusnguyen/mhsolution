package com.mh.core.mhsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.mh.core")
public class MhsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MhsApiApplication.class, args);
    }

}
