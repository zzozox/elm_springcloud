package com.example.user_provider_13001.controller;

import com.example.common.entity.RestBean;
import com.example.common.entity.dto.Account;
import com.example.common.entity.vo.request.account.*;
import com.example.common.entity.vo.response.AuthorizeVO;
import com.example.common.utils.JwtUtils;
import com.example.user_provider_13001.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.function.Supplier;


/**
 * 用于验证相关Controller包含用户的注册、重置密码等操作
 */
@Validated
@RestController
@RequestMapping("/api/auth")
@Tag(name = "登录校验相关", description = "包括用户登录、注册、验证码请求等操作。")
public class AuthorizeController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Resource
    AccountService accountService;

    @Resource
    JwtUtils jwtUtils;

    @PostMapping("/login")
    @Operation(summary = "用户登录")
    public RestBean<AuthorizeVO> login(@RequestBody @Valid LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            User user = (User) authentication.getPrincipal();
            Account account = accountService.findAccountByNameOrEmail(user.getUsername());
            String jwt = jwtUtils.createJwt(user, account.getUsername(), account.getUserId());
            if (jwt == null) {
                return RestBean.forbidden("登录验证频繁，请稍后再试");
            } else {
                AuthorizeVO vo = account.asViewObject(AuthorizeVO.class, o -> o.setToken(jwt));
                vo.setExpire(jwtUtils.expireTime());
                return RestBean.success(vo);
            }
        } catch (AuthenticationException e) {
            return RestBean.forbidden("用户名或密码错误");
        }
    }

    @PostMapping("/logout")
    @Operation(summary = "用户退出登录")
    public RestBean<String> logout(@RequestHeader("Authorization") String authorization) {
        if (jwtUtils.invalidateJwt(authorization)) {
            return RestBean.success("退出登录成功");
        } else {
            return RestBean.failure(400, "退出登录失败");
        }
    }

    /**
     * 请求邮件验证码
     * @param email 请求邮件
     * @param type 类型
     * @param request 请求
     * @return 是否请求成功
     */
    @GetMapping("/ask-code")
    @Operation(summary = "请求邮件验证码")
    public RestBean<Void> askVerifyCode(@RequestParam @Email String email,
                                        @RequestParam @Pattern(regexp = "(register|reset)")  String type,
                                        HttpServletRequest request){
        return this.messageHandle(() ->
                accountService.registerEmailVerifyCode(type, String.valueOf(email), request.getRemoteAddr()));
    }

    /**
     * 进行用户注册操作，需要先请求邮件验证码
     * @param vo 注册信息
     * @return 是否注册成功
     */
    @PostMapping("/register")
    @Operation(summary = "用户注册操作")
    public RestBean<Void> register(@RequestBody @Valid EmailRegisterVO vo){
        return this.messageHandle(() ->
                accountService.registerEmailAccount(vo));
    }

    /**
     * 执行密码重置确认，检查验证码是否正确
     * @param vo 密码重置信息
     * @return 是否操作成功
     */
    @PostMapping("/reset-confirm")
    @Operation(summary = "密码重置确认")
    public RestBean<Void> resetConfirm(@RequestBody @Valid ConfirmResetVO vo){
        return this.messageHandle(() -> accountService.resetConfirm(vo));
    }

    /**
     * 执行密码重置操作
     * @param vo 密码重置信息
     * @return 是否操作成功
     */
    @PostMapping("/reset-password")
    @Operation(summary = "密码重置操作")
    public RestBean<Void> resetPassword(@RequestBody @Valid EmailResetVO vo){
        return this.messageHandle(() ->
                accountService.resetEmailAccountPassword(vo));
    }
    /**
     * 根据用户id查询用户信息
     * @param userId
     * @return
     */
    @PostMapping("/index/{userId}")
    @Operation(summary = "获取用户信息")
    public RestBean<Account> getUserById(@PathVariable Integer userId){
        return RestBean.success(accountService.findAccountById(userId));
    }

    /**
     * 修改用户信息
     * @param vo
     * @return
     */
    @PostMapping("/updateUser")
    @Operation(summary = "修改用户信息")
    public RestBean<Void> updateUser(@RequestBody @Valid UpdataAccountVo vo){
        return  this.messageHandle(() ->
                accountService.updateAccount(vo));
    }
    /**
     * 针对于返回值为String作为错误信息的方法进行统一处理
     * @param action 具体操作
     * @return 响应结果
     * @param <T> 响应结果类型
     */
    private <T> RestBean<T> messageHandle(Supplier<String> action){
        String message = action.get();
        if(message == null)
            return RestBean.success();
        else
            return RestBean.failure(400, message);
    }
}
