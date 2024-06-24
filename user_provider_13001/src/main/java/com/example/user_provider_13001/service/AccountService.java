package com.example.user_provider_13001.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.entity.dto.Account;
import com.example.common.entity.vo.UpdataAccountVo;
import com.example.common.entity.vo.request.ConfirmResetVO;
import com.example.common.entity.vo.request.EmailRegisterVO;
import com.example.common.entity.vo.request.EmailResetVO;
import jakarta.validation.Valid;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccountService extends IService<Account>, UserDetailsService {
    Account findAccountByNameOrEmail(String text);
    String registerEmailVerifyCode(String type, String email, String address);
    String registerEmailAccount(@Valid EmailRegisterVO info);
    String resetEmailAccountPassword(@Valid EmailResetVO info);
    String resetConfirm(@Valid ConfirmResetVO info);
    Account findAccountById(Integer userId);
    String updateAccount(@Valid UpdataAccountVo updataAccountVo);
}
