package com.example.order_consumer_13012;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"com.example.order_consumer_13012", "com.example.common"})
public class OrderConsumer13012Application {

    public static void main(String[] args) {
        SpringApplication.run(OrderConsumer13012Application.class, args);
    }

}
