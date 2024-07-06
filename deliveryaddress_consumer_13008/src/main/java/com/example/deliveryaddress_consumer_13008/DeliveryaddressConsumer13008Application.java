package com.example.deliveryaddress_consumer_13008;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"com.example.deliveryaddress_consumer_13008", "com.example.common"})
public class DeliveryaddressConsumer13008Application {

    public static void main(String[] args) {
        SpringApplication.run(DeliveryaddressConsumer13008Application.class, args);
    }

}
