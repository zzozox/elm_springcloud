package com.example.orderdetailet_provider_13013;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.example.orderdetailet_provider_13013", "com.example.common"})
public class OrderdetailetProvider13013Application {

    public static void main(String[] args) {
        SpringApplication.run(OrderdetailetProvider13013Application.class, args);
    }

}
