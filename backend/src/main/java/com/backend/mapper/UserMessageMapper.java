package com.backend.mapper;

import com.backend.entity.Comment;
import com.backend.entity.UserMessage;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMessageMapper {

    @Select("select * from blog.user_message order by send_time")
    @Results({
            @Result(property = "messageId", column = "message_id"),
            @Result(property = "sender", column = "sender_id", one = @One(select = "com.backend.mapper.UserUserMapper.SelectOnefindByUserId")),
            @Result(property = "receiver", column = "receiver_id", one = @One(select = "com.backend.mapper.UserUserMapper.SelectOnefindByUserId")),
            @Result(property = "content", column = "content"),
            @Result(property = "messageStatus", column = "message_status"),
            @Result(property = "sendTime", column = "send_time"),
    })
    List<UserMessage> findAllUserMessageInASC();

    @Select("select * from blog.user_message order by send_time desc")
    @Results({
            @Result(property = "messageId", column = "message_id"),
            @Result(property = "sender", column = "sender_id", one = @One(select = "com.backend.mapper.UserUserMapper.SelectOnefindByUserId")),
            @Result(property = "receiver", column = "receiver_id", one = @One(select = "com.backend.mapper.UserUserMapper.SelectOnefindByUserId")),
            @Result(property = "content", column = "content"),
            @Result(property = "messageStatus", column = "message_status"),
            @Result(property = "sendTime", column = "send_time"),
    })
    List<UserMessage> findAllUserMessageInDESC();

    @Select("""
                call getBySenderNameUserMessage(#{name})
            """)
    @Results({
            @Result(property = "messageId", column = "message_id"),
            @Result(property = "sender", column = "sender_id", one = @One(select = "com.backend.mapper.UserUserMapper.SelectOnefindByUserId")),
            @Result(property = "receiver", column = "receiver_id", one = @One(select = "com.backend.mapper.UserUserMapper.SelectOnefindByUserId")),
            @Result(property = "content", column = "content"),
            @Result(property = "messageStatus", column = "message_status"),
            @Result(property = "sendTime", column = "send_time"),
    })
    List<UserMessage> findUserMessageBySenderName(String name);

    @Select("""
                call getByReceiverNameUserMessage(#{name})
            """)
    @Results({
            @Result(property = "messageId", column = "message_id"),
            @Result(property = "sender", column = "sender_id", one = @One(select = "com.backend.mapper.UserUserMapper.SelectOnefindByUserId")),
            @Result(property = "receiver", column = "receiver_id", one = @One(select = "com.backend.mapper.UserUserMapper.SelectOnefindByUserId")),
            @Result(property = "content", column = "content"),
            @Result(property = "messageStatus", column = "message_status"),
            @Result(property = "sendTime", column = "send_time"),
    })
    List<UserMessage> findUserMessageByReceiverName(String name);

    @Select("select * from blog.user_message where content like concat('%',#{content},'%')")
    @Results({
            @Result(property = "messageId", column = "message_id"),
            @Result(property = "sender", column = "sender_id", one = @One(select = "com.backend.mapper.UserUserMapper.SelectOnefindByUserId")),
            @Result(property = "receiver", column = "receiver_id", one = @One(select = "com.backend.mapper.UserUserMapper.SelectOnefindByUserId")),
            @Result(property = "content", column = "content"),
            @Result(property = "messageStatus", column = "message_status"),
            @Result(property = "sendTime", column = "send_time"),
    })
    List<UserMessage> findUserMessageByContent(String content);

    @Select("select * from blog.user_message where DATE(send_time) = #{day}")
    @Results({
            @Result(property = "messageId", column = "message_id"),
            @Result(property = "sender", column = "sender_id", one = @One(select = "com.backend.mapper.UserUserMapper.SelectOnefindByUserId")),
            @Result(property = "receiver", column = "receiver_id", one = @One(select = "com.backend.mapper.UserUserMapper.SelectOnefindByUserId")),
            @Result(property = "content", column = "content"),
            @Result(property = "messageStatus", column = "message_status"),
            @Result(property = "sendTime", column = "send_time"),
    })
    List<UserMessage> findUserMessageByDay(String day);

    @Select("select * from blog.user_message where message_status = #{info}")
    @Results({
            @Result(property = "messageId", column = "message_id"),
            @Result(property = "sender", column = "sender_id", one = @One(select = "com.backend.mapper.UserUserMapper.SelectOnefindByUserId")),
            @Result(property = "receiver", column = "receiver_id", one = @One(select = "com.backend.mapper.UserUserMapper.SelectOnefindByUserId")),
            @Result(property = "content", column = "content"),
            @Result(property = "messageStatus", column = "message_status"),
            @Result(property = "sendTime", column = "send_time"),
    })
    List<UserMessage> findUserMessageByMessageStatus(int info);

    @Select("select * from blog.user_message where message_id = #{id}")
    @Results({
            @Result(property = "messageId", column = "message_id"),
            @Result(property = "sender", column = "sender_id", one = @One(select = "com.backend.mapper.UserUserMapper.SelectOnefindByUserId")),
            @Result(property = "receiver", column = "receiver_id", one = @One(select = "com.backend.mapper.UserUserMapper.SelectOnefindByUserId")),
            @Result(property = "content", column = "content"),
            @Result(property = "messageStatus", column = "message_status"),
            @Result(property = "sendTime", column = "send_time"),
    })
    UserMessage findUserMessageByMessageId(int id);

    @Delete("delete from blog.user_message where message_id = #{id}")
    boolean deleteUserMessageByMessageId(int id);

    @Delete("delete from blog.user_message where sender_id = #{id}")
    boolean deleteUserMessageBySenderId(int id);

    @Delete("delete from blog.user_message where receiver_id = #{id}")
    boolean deleteUserMessageByReceiverId(int id);
    @Select("""
            select * from user_message where
            sender_id = 1 or receiver_id = 1
            order by send_time desc
            """)
    @Results({
            @Result(property = "messageId", column = "message_id"),
            @Result(property = "sender", column = "sender_id", one = @One(select = "com.backend.mapper.UserUserMapper.SelectOnefindByUserId")),
            @Result(property = "receiver", column = "receiver_id", one = @One(select = "com.backend.mapper.UserUserMapper.SelectOnefindByUserId")),
            @Result(property = "content", column = "content"),
            @Result(property = "messageStatus", column = "message_status"),
            @Result(property = "sendTime", column = "send_time"),
    })
    List<UserMessage> findUserMessageBySenderIdOrReceiverId(int id);
}
