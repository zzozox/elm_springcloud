package com.example.user_provider_13001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.example.user_provider_13001", "com.example.common"})
public class UserProvider13001Application {

    public static void main(String[] args) {
        SpringApplication.run(UserProvider13001Application.class, args);
    }

}
