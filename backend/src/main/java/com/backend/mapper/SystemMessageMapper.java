package com.backend.mapper;

import com.backend.entity.Course;
import com.backend.entity.SystemMessage;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SystemMessageMapper {

    @Select("select * from blog.system_message order by send_time")
    @Results({
            @Result(property = "messageId", column = "message_id"),
            @Result(property = "content", column = "content"),
            @Result(property = "messageStatus", column = "message_status"),
            @Result(property = "sendTime", column = "send_time"),
            @Result(property = "userAccount", column = "user_account_username", one = @One(select = "com.backend.mapper.UserAccountMapper.findByIdOneUserAccount")),
    })
    List<SystemMessage> findAllSystemMessageInASC();

    @Select("select * from blog.system_message order by send_time desc")
    @Results({
            @Result(property = "messageId", column = "message_id"),
            @Result(property = "content", column = "content"),
            @Result(property = "messageStatus", column = "message_status"),
            @Result(property = "sendTime", column = "send_time"),
            @Result(property = "userAccount", column = "user_account_username", one = @One(select = "com.backend.mapper.UserAccountMapper.findByIdOneUserAccount")),
    })
    List<SystemMessage> findAllSystemMessageInDESC();

    @Select("""
                call getByUserAccountUsernameSystemMessage(#{name})
            """)
    @Results({
            @Result(property = "messageId", column = "message_id"),
            @Result(property = "content", column = "content"),
            @Result(property = "messageStatus", column = "message_status"),
            @Result(property = "sendTime", column = "send_time"),
            @Result(property = "userAccount", column = "user_account_username", one = @One(select = "com.backend.mapper.UserAccountMapper.findByIdOneUserAccount")),
    })
    List<SystemMessage> findSystemMessageByUserAccountUsername(String name);

    @Select("select * from blog.system_message where content like concat('%',#{content},'%')")
    @Results({
            @Result(property = "messageId", column = "message_id"),
            @Result(property = "content", column = "content"),
            @Result(property = "messageStatus", column = "message_status"),
            @Result(property = "sendTime", column = "send_time"),
            @Result(property = "userAccount", column = "user_account_username", one = @One(select = "com.backend.mapper.UserAccountMapper.findByIdOneUserAccount")),
    })
    List<SystemMessage> findSystemMessageByContent(String content);

    @Select("select * from blog.system_message where DATE(send_time) = #{day}")
    @Results({
            @Result(property = "messageId", column = "message_id"),
            @Result(property = "content", column = "content"),
            @Result(property = "messageStatus", column = "message_status"),
            @Result(property = "sendTime", column = "send_time"),
            @Result(property = "userAccount", column = "user_account_username", one = @One(select = "com.backend.mapper.UserAccountMapper.findByIdOneUserAccount")),
    })
    List<SystemMessage> findSystemMessageByDay(String day);

    @Select("select * from blog.system_message where message_status = #{info}")
    @Results({
            @Result(property = "messageId", column = "message_id"),
            @Result(property = "content", column = "content"),
            @Result(property = "messageStatus", column = "message_status"),
            @Result(property = "sendTime", column = "send_time"),
            @Result(property = "userAccount", column = "user_account_username", one = @One(select = "com.backend.mapper.UserAccountMapper.findByIdOneUserAccount")),
    })
    List<SystemMessage> findSystemMessageByMessageStatus(int info);

    @Select("select * from blog.system_message where message_id = #{id}")
    @Results({
            @Result(property = "messageId", column = "message_id"),
            @Result(property = "content", column = "content"),
            @Result(property = "messageStatus", column = "message_status"),
            @Result(property = "sendTime", column = "send_time"),
            @Result(property = "userAccount", column = "user_account_username", one = @One(select = "com.backend.mapper.UserAccountMapper.findByIdOneUserAccount")),
    })
    SystemMessage findSystemMessageByMessageId(int id);

    @Insert("""
            insert into blog.system_message (content,send_time,user_account_username) VALUES
            (#{content},#{sendTime},#{userAccount.userAccountUsername})
            """)
    boolean insertSystemMessage(SystemMessage systemMessage);

    @Delete("delete from blog.system_message where message_id = #{id}")
    boolean deleteSystemMessageByMessageId(int id);

    @Delete("delete from blog.system_message where user_account_username = #{userAccountUsername}")
    boolean deleteSystemMessageByUserAccountUsername(String userAccountUsername);

    @Delete("delete from blog.system_message where send_time = #{day}")
    boolean deleteSystemMessageByDay(String day);

}
