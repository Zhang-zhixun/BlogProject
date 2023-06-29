package com.backend.service.impl;

import com.backend.entity.UserMessage;
import com.backend.mapper.UserMessageMapper;
import com.backend.service.UserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMessageServiceImpl implements UserMessageService {

    @Autowired
    UserMessageMapper userMessageMapper;

    @Override
    public List<UserMessage> findAllUserMessageInASC() {
        return userMessageMapper.findAllUserMessageInASC();
    }

    @Override
    public List<UserMessage> findAllUserMessageInDESC() {
        return userMessageMapper.findAllUserMessageInDESC();
    }

    @Override
    public List<UserMessage> findUserMessageBySenderName(String name) {
        return userMessageMapper.findUserMessageBySenderName(name);
    }

    @Override
    public List<UserMessage> findUserMessageByReceiverName(String name) {
        return userMessageMapper.findUserMessageByReceiverName(name);
    }

    @Override
    public List<UserMessage> findUserMessageByContent(String content) {
        return userMessageMapper.findUserMessageByContent(content);
    }

    @Override
    public List<UserMessage> findUserMessageByDay(String day) {
        return userMessageMapper.findUserMessageByDay(day);
    }

    @Override
    public List<UserMessage> findUserMessageByMessageStatus(int info) {
        return userMessageMapper.findUserMessageByMessageStatus(info);
    }

    @Override
    public UserMessage findUserMessageByMessageId(int id) {
        return userMessageMapper.findUserMessageByMessageId(id);
    }

    @Override
    public boolean deleteUserMessageByMessageId(int id) {
        return userMessageMapper.deleteUserMessageByMessageId(id);
    }

    @Override
    public boolean deleteUserMessageBySenderId(int id) {
        return userMessageMapper.deleteUserMessageBySenderId(id);
    }

    @Override
    public boolean deleteUserMessageByReceiverId(int id) {
        return userMessageMapper.deleteUserMessageByReceiverId(id);
    }

    @Override
    public List<UserMessage> findUserMessageBySenderIdOrReceiverId(int id) {
        return userMessageMapper.findUserMessageBySenderIdOrReceiverId(id);
    }
}
