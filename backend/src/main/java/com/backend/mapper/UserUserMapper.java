package com.backend.mapper;

import com.backend.entity.User;
import com.backend.entity.UserAccount;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserUserMapper {

    @Select("select * from blog.user")
    @Results({
            @Result(property = "user_id", column = "userId"),
            @Result(property = "name", column = "name"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "age", column = "age"),
            @Result(property = "school", column = "school"),
            @Result(property = "email", column = "user_account_username",
                    javaType = String.class, //要封装的实体类型
                    one = @One(select = "com.backend.mapper.UserAccountMapper.findByIdOneUserEmailAccount")),
            @Result(property = "identity", column = "identity"),
            @Result(property = "userAccount", column = "user_account_username",
                    javaType = UserAccount.class, //要封装的实体类型
                    one = @One(select = "com.backend.mapper.UserAccountMapper.findByIdOneUserAccount"))
    })
    List<User> findByAllUser();

/*  可以
    @Select("select * from blog.user")
    @Results({
            @Result(property = "user_id", column = "userId"),
            @Result(property = "name", column = "name"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "age", column = "age"),
            @Result(property = "school", column = "school"),
            @Result(property = "identity", column = "identity"),
            @Result(
                    property = "userAccount", column = "user_account_username",
                    javaType = UserAccount.class, //要封装的实体类型
                    one = @One(select = "com.backend.mapper.UserAccountMapper.findByIdOneUserAccount"))
    })
*/




    @Select("select * from blog.user limit #{page},#{size} ")
    List<User> findByAllUserPaging(Integer page, Integer size);//分页获取全部用户

    @Delete("DELETE FROM blog.user WHERE user.user_id =#{id}")
    int DelectUserId(int id);//删除

    @Select("SELECT * FROM blog.user WHERE user_id =#{id}")
    @Results({
            @Result(property = "user_id", column = "userId"),
            @Result(property = "name", column = "name"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "age", column = "age"),
            @Result(property = "school", column = "school"),
            @Result(property = "email", column = "user_account_username",
                    javaType = String.class, //要封装的实体类型
                    one = @One(select = "com.backend.mapper.UserAccountMapper.findByIdOneUserEmailAccount")),
            @Result(property = "identity", column = "identity"),
            @Result(property = "userAccount", column = "user_account_username",
                    javaType = UserAccount.class, //要封装的实体类型
                    one = @One(select = "com.backend.mapper.UserAccountMapper.findByIdOneUserAccount"))
    })
    User SelectOnefindByUserId(int id);//显示目标用户的信息

    /*  UPDATE blog.user
              SET name = #{user.name}, gender = #{user.gender}, age = #{user.age}, school = #{user.school}, identity = #{user.identity}, user_account_username = #{user.userAccountUsername}
              WHERE user_id = #{user.userId}
              */
    @Update("""
            UPDATE blog.user INNER JOIN  blog.user_account  ON user.user_account_username= user_account.user_account_username
            SET  user.age = #{user.age},user_account.email = #{user.userAccount.email},name =#{user.name}, gender =#{user.gender} , school =#{user.school}, identity = #{user.identity}
            WHERE user.user_account_username = #{user.userAccount.userAccountUsername}
            """)
    int UpdateUserOneByUserId(@Param("user") User user);


    /*select * from blog.user WHERE user.name LIKE CONCAT('%',#{name},'%')*/
    @Select("""
            select *
            from blog.user INNER JOIN  blog.user_account  ON user.user_account_username= user_account.user_account_username
            WHERE user.name LIKE CONCAT('%',#{name},'%')         
            """)
    @Results({
            @Result(property = "user_id", column = "userId"),
            @Result(property = "name", column = "name"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "age", column = "age"),
            @Result(property = "school", column = "school"),
            @Result(property = "email", column = "user_account_username",
                    javaType = String.class, //要封装的实体类型
                    one = @One(select = "com.backend.mapper.UserAccountMapper.findByIdOneUserEmailAccount")),
            @Result(property = "identity", column = "identity"),
            @Result(property = "userAccount", column = "user_account_username",
                    javaType = UserAccount.class, //要封装的实体类型
                    one = @One(select = "com.backend.mapper.UserAccountMapper.findByIdOneUserAccount"))
    })
    List<User> SelectNameLinkFindByUser(@Param("name") String name);//按照名字模糊查询





    //连表查询
    @Select("select * from blog.user")
    @Results({
            @Result(column = "userAccountUsername", property = "userAccountUsername", one =
            @One(select = "com/backend/mapper/UserAccountMapper.java"))})
    List<User> findByAllUserlbcx();





}
