package com.backend.mapper;

import com.backend.entity.User;
import com.backend.entity.UserAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserAccountMapper {

    @Select("SELECT * FROM blog.user_account")
    List<UserAccount> findByAllUserAccount();

    @Select("SELECT * FROM blog.user_account WHERE user_account_username=#{user_account_username}")
    UserAccount findByIdOneUserAccount(String user_account_username);

    @Select("SELECT email FROM blog.user_account WHERE user_account_username=#{user_account_username}")
    String findByIdOneUserEmailAccount(String user_account_username);
    @Select("SELECT user_account_username FROM blog.user_account")
    List<UserAccount> findByAlluser_account_usernameAccount();  //打印出全部的 user_account_username 用来选择


}
