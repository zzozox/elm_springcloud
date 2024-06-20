package com.example.user_consumer_13002.controller.apiClient;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "user_provider_13001", fallback = UserApiClientFallback.class)
@LoadBalancerClient(name = "user_provider_13001")
public interface UserApiClient {

}
