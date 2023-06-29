package com.backend.service.impl;

import com.backend.entity.UserAccount;
import com.backend.mapper.UserAccountMapper;
import com.backend.service.UserAccountService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserAccountServiceImpl implements UserAccountService {
    @Autowired
    UserAccountMapper userAccountMapper;
    @Override
    public List<UserAccount> findByAllUserAccount() {
        return userAccountMapper.findByAllUserAccount();
    }

    @Override
    public UserAccount findByIdOneUserAccount(String user_account_username) {
        return userAccountMapper.findByIdOneUserAccount(user_account_username);
    }

    @Override
    public String findByIdOneUserEmailAccount(String user_account_username) {
        return userAccountMapper.findByIdOneUserEmailAccount(user_account_username);
    }

    @Override
    public List<UserAccount> findByAlluser_account_usernameAccount() {        //打印出全部的 user_account_username 用来选择
        return userAccountMapper.findByAlluser_account_usernameAccount();
    }
}
