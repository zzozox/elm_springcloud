package com.example.cart_provider_13009;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.example.cart_provider_13009", "com.example.common"})
public class CartProvider13009Application {

    public static void main(String[] args) {
        SpringApplication.run(CartProvider13009Application.class, args);
    }

}
