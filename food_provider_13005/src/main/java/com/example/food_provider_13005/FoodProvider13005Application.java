package com.example.food_provider_13005;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.example.food_provider_13005", "com.example.common"})
public class FoodProvider13005Application {

    public static void main(String[] args) {
        SpringApplication.run(FoodProvider13005Application.class, args);
    }

}
