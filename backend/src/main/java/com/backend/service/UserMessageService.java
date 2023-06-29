package com.backend.service;

import com.backend.entity.UserMessage;

import java.util.List;

public interface UserMessageService {
    List<UserMessage> findAllUserMessageInASC();

    List<UserMessage> findAllUserMessageInDESC();

    List<UserMessage> findUserMessageBySenderName(String name);

    List<UserMessage> findUserMessageByReceiverName(String name);

    List<UserMessage> findUserMessageByContent(String content);

    List<UserMessage> findUserMessageByDay(String day);

    List<UserMessage> findUserMessageByMessageStatus(int info);

    UserMessage findUserMessageByMessageId(int id);

    boolean deleteUserMessageByMessageId(int id);

    boolean deleteUserMessageBySenderId(int id);

    boolean deleteUserMessageByReceiverId(int id);




}
