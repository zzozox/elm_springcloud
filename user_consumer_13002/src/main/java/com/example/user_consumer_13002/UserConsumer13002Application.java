package com.example.user_consumer_13002;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"com.example.user_consumer_13002", "com.example.common"})
public class UserConsumer13002Application {

    public static void main(String[] args) {
        SpringApplication.run(UserConsumer13002Application.class, args);
    }

}
