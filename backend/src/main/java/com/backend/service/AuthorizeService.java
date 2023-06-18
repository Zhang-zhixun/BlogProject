package com.backend.service;

import com.backend.entity.auth.AdminAccount;
import com.backend.entity.auth.Admin;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthorizeService extends UserDetailsService {
    //发送邮箱验证码
    String sendValidateEmail(String email,String sessionId,String hasAccount);
    //注册

    String registerAccount(AdminAccount adminAccount, String code);

    //重置密码时 验证邮件验证码
    String validateOnly(String email,String code,String sessionId);
    //重置密码
    boolean resetPassword(String password,String email);
    //管理员个人信息查询
    Admin findAllAccount(String username);
    boolean updateAccount(Admin admin);

}
