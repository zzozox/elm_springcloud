package com.example.user_consumer_13002.controller;

import com.example.common.entity.RestBean;
import com.example.common.entity.dto.Account;
import com.example.common.entity.vo.request.account.*;
import com.example.common.entity.vo.response.AuthorizeVO;
import com.example.user_consumer_13002.controller.apiClient.UserApiClient;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Resource
    private UserApiClient userApiClient;

    @PostMapping("/login")
    public RestBean<AuthorizeVO> login(@RequestBody LoginRequest loginRequest) {
        return userApiClient.login(loginRequest);
    }

    @PostMapping("/logout")
    public RestBean<String> logout(@RequestHeader("Authorization") String authorization) {
        return userApiClient.logout(authorization);
    }

    @GetMapping("/ask-code")
    public RestBean<Void> askVerifyCode(@RequestParam String email, @RequestParam String type) {
        return userApiClient.askVerifyCode(email, type);
    }

    @PostMapping("/register")
    public RestBean<Void> register(@RequestBody EmailRegisterVO vo) {
        return userApiClient.register(vo);
    }

    @PostMapping("/reset-confirm")
    public RestBean<Void> resetConfirm(@RequestBody ConfirmResetVO vo) {
        return userApiClient.resetConfirm(vo);
    }

    @PostMapping("/reset-password")
    public RestBean<Void> resetPassword(@RequestBody EmailResetVO vo) {
        return userApiClient.resetPassword(vo);
    }

    @PostMapping("/index/{userId}")
    public RestBean<Account> getUserById(@PathVariable Integer userId) {
        return userApiClient.getUserById(userId);
    }

    @PostMapping("/updateUser")
    public RestBean<Void> updateUser(@RequestBody UpdataAccountVo vo) {
        return userApiClient.updateUser(vo);
    }
}
