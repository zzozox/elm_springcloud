package com.example.food_consumer_13006;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"com.example.food_consumer_13006", "com.example.common"})
public class FoodConsumer13006Application {

    public static void main(String[] args) {
        SpringApplication.run(FoodConsumer13006Application.class, args);
    }

}
