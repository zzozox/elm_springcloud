package com.example.deliveryaddress_provider_13007;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.example.deliveryaddress_provider_13007", "com.example.common"})
public class DeliveryaddressProvider13007Application {

    public static void main(String[] args) {
        SpringApplication.run(DeliveryaddressProvider13007Application.class, args);
    }

}
