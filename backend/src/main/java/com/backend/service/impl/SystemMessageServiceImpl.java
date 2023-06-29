package com.backend.service.impl;

import com.backend.entity.SystemMessage;
import com.backend.mapper.SystemMessageMapper;
import com.backend.service.SystemMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SystemMessageServiceImpl implements SystemMessageService {

    @Autowired
    SystemMessageMapper systemMessageMapper;

    @Override
    public List<SystemMessage> findAllSystemMessageInASC() {
        return systemMessageMapper.findAllSystemMessageInASC();
    }

    @Override
    public List<SystemMessage> findAllSystemMessageInDESC() {
        return systemMessageMapper.findAllSystemMessageInDESC();
    }

    @Override
    public List<SystemMessage> findSystemMessageByUserAccountUsername(String name) {
        return systemMessageMapper.findSystemMessageByUserAccountUsername(name);
    }

    @Override
    public List<SystemMessage> findSystemMessageByContent(String content) {
        return systemMessageMapper.findSystemMessageByContent(content);
    }

    @Override
    public List<SystemMessage> findSystemMessageByDay(String day) {
        return systemMessageMapper.findSystemMessageByDay(day);
    }

    @Override
    public List<SystemMessage> findSystemMessageByMessageStatus(int info) {
        return systemMessageMapper.findSystemMessageByMessageStatus(info);
    }

    @Override
    public SystemMessage findSystemMessageByMessageId(int id) {
        return systemMessageMapper.findSystemMessageByMessageId(id);
    }

    @Override
    public boolean insertSystemMessage(SystemMessage systemMessage) {
        return systemMessageMapper.insertSystemMessage(systemMessage);
    }

    @Override
    public boolean deleteSystemMessageByMessageId(int id) {
        return systemMessageMapper.deleteSystemMessageByMessageId(id);
    }

    @Override
    public boolean deleteSystemMessageByUserAccountUsername(String userAccountUsername) {
        return systemMessageMapper.deleteSystemMessageByUserAccountUsername(userAccountUsername);
    }

    @Override
    public boolean deleteSystemMessageByDay(String day) {
        return systemMessageMapper.deleteSystemMessageByDay(day);
    }
}
