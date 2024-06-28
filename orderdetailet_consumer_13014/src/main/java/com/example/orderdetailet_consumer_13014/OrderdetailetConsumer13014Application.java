package com.example.orderdetailet_consumer_13014;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"com.example.orderdetailet_consumer_13014", "com.example.common"})
public class OrderdetailetConsumer13014Application {

    public static void main(String[] args) {
        SpringApplication.run(OrderdetailetConsumer13014Application.class, args);
    }

}
