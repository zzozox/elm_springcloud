package com.example.order_provider_13011;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {
        "com.example.order_provider_13011",
        "com.example.common"})
public class OrderProvider13011Application {

    public static void main(String[] args) {
        SpringApplication.run(OrderProvider13011Application.class, args);
    }

}
