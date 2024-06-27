package com.example.user_consumer_13002.controller.apiClient;

import com.example.common.entity.RestBean;
import com.example.common.entity.dto.Account;
import com.example.common.entity.vo.request.account.*;
import com.example.common.entity.vo.response.AuthorizeVO;
import org.springframework.stereotype.Component;

@Component
public class UserApiClientFallback implements UserApiClient {

    @Override
    public RestBean<AuthorizeVO> login(LoginRequest loginRequest) {
        return RestBean.failure(500, "服务不可用，无法登录");
    }

    @Override
    public RestBean<String> logout(String authorization) {
        return RestBean.failure(500, "服务不可用，无法退出登录");
    }

    @Override
    public RestBean<Void> askVerifyCode(String email, String type) {
        return RestBean.failure(500, "服务不可用，无法请求验证码");
    }

    @Override
    public RestBean<Void> register(EmailRegisterVO vo) {
        return RestBean.failure(500, "服务不可用，无法注册");
    }

    @Override
    public RestBean<Void> resetConfirm(ConfirmResetVO vo) {
        return RestBean.failure(500, "服务不可用，无法确认重置密码");
    }

    @Override
    public RestBean<Void> resetPassword(EmailResetVO vo) {
        return RestBean.failure(500, "服务不可用，无法重置密码");
    }

    @Override
    public RestBean<Account> getUserById(Integer userId) {
        return RestBean.failure(500, "服务不可用，无法获取用户信息");
    }

    @Override
    public RestBean<Void> updateUser(UpdataAccountVo vo) {
        return RestBean.failure(500, "服务不可用，无法更新用户信息");
    }
}