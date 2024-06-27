package com.example.business_provider_13003;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.example.business_provider_13003", "com.example.common"})
public class BusinessProvider13003Application {

    public static void main(String[] args) {
        SpringApplication.run(BusinessProvider13003Application.class, args);
    }

}
