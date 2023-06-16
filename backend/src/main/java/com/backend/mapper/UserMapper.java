package com.backend.mapper;

import com.backend.entity.auth.AdminAccount;
import com.backend.entity.auth.Admin;
import com.backend.entity.user.AccountUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Select("select * from blog.admin_account where admin_username = #{text} or email = #{text}")
    AdminAccount findByUsernameOrEmail(String text);

    @Select("select * from blog.admin_account where admin_username = #{text} or email = #{text}")
    AccountUser findByAccountUsernameOrEmail(String text);

    @Insert("insert into blog.admin_account(email, admin_username, password) values(#{email},#{adminUsername},#{password})")
    int registerAccount(AdminAccount adminAccount);

    @Update("update blog.admin_account set password = #{password} where email = #{email}")
    int resetPasswordByEmail(String password, String email);

    @Select("select * from blog.account_info where admin_username = #{adminUsername}")
    Admin findAllAccount(String username);

    @Update("""
            update blog.account_info set address = #{address},age = #{age},birthday = #{birthday},
            name = #{name},phone = #{phone},idCard = #{idCard},sex = #{sex},signature = #{signature}  
            where admin_username=#{adminUsername};
            """)
    boolean updateAccount(Admin admin);
}
