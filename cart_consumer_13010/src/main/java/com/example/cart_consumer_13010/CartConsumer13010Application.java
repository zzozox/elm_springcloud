package com.example.cart_consumer_13010;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"com.example.cart_consumer_13010", "com.example.common"})
public class CartConsumer13010Application {

    public static void main(String[] args) {
        SpringApplication.run(CartConsumer13010Application.class, args);
    }

}
