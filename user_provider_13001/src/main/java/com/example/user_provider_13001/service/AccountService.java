package com.example.user_provider_13001.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.entity.dto.Account;
import com.example.common.entity.vo.request.account.ConfirmResetVO;
import com.example.common.entity.vo.request.account.EmailRegisterVO;
import com.example.common.entity.vo.request.account.EmailResetVO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccountService extends IService<Account>, UserDetailsService {
    Account findAccountByNameOrEmail(String text);
    String registerEmailVerifyCode(String type, String email, String address);
    String registerEmailAccount(EmailRegisterVO info);
    String resetEmailAccountPassword(EmailResetVO info);
    String resetConfirm(ConfirmResetVO info);
    Account findAccountById(Integer userId);
    String updateAccount(Account account);
}
