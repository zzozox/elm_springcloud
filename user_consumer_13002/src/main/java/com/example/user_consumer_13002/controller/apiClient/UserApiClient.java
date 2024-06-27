package com.example.user_consumer_13002.controller.apiClient;

import com.example.common.entity.RestBean;
import com.example.common.entity.dto.Account;
import com.example.common.entity.vo.request.account.*;
import com.example.common.entity.vo.response.AuthorizeVO;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user-provider-13001", fallback = UserApiClientFallback.class)
@LoadBalancerClient(name = "user-provider-13001")
public interface UserApiClient {

    @PostMapping("/api/auth/login")
    RestBean<AuthorizeVO> login(@RequestBody LoginRequest loginRequest);

    @PostMapping("/api/auth/logout")
    RestBean<String> logout(@RequestHeader("Authorization") String authorization);

    @GetMapping("/api/auth/ask-code")
    RestBean<Void> askVerifyCode(@RequestParam String email, @RequestParam String type);

    @PostMapping("/api/auth/register")
    RestBean<Void> register(@RequestBody EmailRegisterVO vo);

    @PostMapping("/api/auth/reset-confirm")
    RestBean<Void> resetConfirm(@RequestBody ConfirmResetVO vo);

    @PostMapping("/api/auth/reset-password")
    RestBean<Void> resetPassword(@RequestBody EmailResetVO vo);

    @PostMapping("/api/auth/index/{userId}")
    RestBean<Account> getUserById(@PathVariable Integer userId);

    @PostMapping("/api/auth/updateUser")
    RestBean<Void> updateUser(@RequestBody UpdataAccountVo vo);
}
