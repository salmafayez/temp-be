package com.example.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class FullStackTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(FullStackTaskApplication.class, args);
    }

}
