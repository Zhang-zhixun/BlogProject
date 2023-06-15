package com.backend.mapper;

import com.backend.entity.auth.Account;
import com.backend.entity.auth.AccountInfo;
import com.backend.entity.user.AccountUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Select("select * from blog.db_account where username = #{text} or email = #{text}")
    Account findByUsernameOrEmail(String text);

    @Select("select * from blog.db_account where username = #{text} or email = #{text}")
    AccountUser findByAccountUsernameOrEmail(String text);

    @Insert("insert into blog.db_account(email, username, password) values(#{email},#{username},#{password})")
    int registerAccount(Account account);

    @Update("update blog.db_account set password = #{password} where email = #{email}")
    int resetPasswordByEmail(String password, String email);

    @Select("select * from blog.account_info where username = #{username}")
    AccountInfo findAllAccount(String username);

    @Update("""
            update blog.account_info set a_address = #{aAddress},a_age = #{aAge},a_birthday = #{aBirthday},
            a_name = #{aName},a_phone = #{aPhone},a_idcard = #{aIdCard},a_sex = #{aSex},a_signature = #{aSignature}  
            where username=#{username};
            """)
    boolean updateAccount(AccountInfo accountInfo);
}
