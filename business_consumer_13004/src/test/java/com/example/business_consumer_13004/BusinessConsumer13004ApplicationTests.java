package com.example.business_consumer_13004;

import com.example.business_consumer_13004.controller.apiClient.BusinessApiClient;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class BusinessConsumer13004ApplicationTests {
    @Resource
    private BusinessApiClient businessApiClient;

    private final String businessProviderUrl = "http://localhost:13003";

    @Test
    void contextLoads() {
        System.out.println(businessApiClient.findBusinessById(1));
    }

    @Test
    void testWithRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiJkZTJmYmEyZS04MTUzLTRiNzktYWYzOC00NGMyMDU3ODVkNTQiLCJpZCI6MSwibmFtZSI6IuWRqOiKt-eShyIsImF1dGhvcml0aWVzIjpbIlJPTEVfdXNlciJdLCJleHAiOjE3MTk4NDcwMzIsImlhdCI6MTcxOTU4NzgzMn0.m-Y7efW1M5sL-52ryV5EKbsMOgR6ZbbbP1HZKfKc6EQ");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:13004/business/byBusinessId/1",
                HttpMethod.POST,
                entity,
                String.class
        );

        System.out.println(response.getBody());
    }
}
