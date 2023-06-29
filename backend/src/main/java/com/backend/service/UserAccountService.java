package com.backend.service;

import com.backend.entity.User;
import com.backend.entity.UserAccount;

import java.util.List;

public interface UserAccountService {
    List<UserAccount> findByAllUserAccount();

    UserAccount findByIdOneUserAccount(String user_account_username);

    String findByIdOneUserEmailAccount(String user_account_username);

    List<UserAccount> findByAlluser_account_usernameAccount();  //打印出全部的 user_account_username 用来选择

}
