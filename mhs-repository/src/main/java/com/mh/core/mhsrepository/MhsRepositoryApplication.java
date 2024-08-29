package com.mh.core.mhsrepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.mh.core")
public class MhsRepositoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(MhsRepositoryApplication.class, args);
    }

}
