package com.example.user_provider_13001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@SpringBootApplication
@EnableDiscoveryClient
public class UserProvider13001Application {

    public static void main(String[] args) {
        SpringApplication.run(UserProvider13001Application.class, args);
    }

}
