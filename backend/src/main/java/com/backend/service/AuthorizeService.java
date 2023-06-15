package com.backend.service;

import com.backend.entity.auth.Account;
import com.backend.entity.auth.AccountInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthorizeService extends UserDetailsService {
    //发送邮箱验证码
    String sendValidateEmail(String email,String sessionId,String hasAccount);
    //注册
    String registerAccount(Account account,String code);
    //重置密码时 验证邮件验证码
    String validateOnly(String email,String code,String sessionId);
    //重置密码
    boolean resetPassword(String password,String email);
    //管理员个人信息查询
    AccountInfo findAllAccount(String username);

    boolean updateAccount(AccountInfo accountInfo);

}
