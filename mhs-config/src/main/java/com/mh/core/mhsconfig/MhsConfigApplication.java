package com.mh.core.mhsconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.mh.core")
public class MhsConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(MhsConfigApplication.class, args);
    }

}
