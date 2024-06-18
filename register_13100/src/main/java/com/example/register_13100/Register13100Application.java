package com.example.register_13100;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Register13100Application {

    public static void main(String[] args) {
        SpringApplication.run(Register13100Application.class, args);
    }

}
