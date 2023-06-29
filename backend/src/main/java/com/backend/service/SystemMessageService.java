package com.backend.service;

import com.backend.entity.SystemMessage;

import java.util.List;

public interface SystemMessageService {

    List<SystemMessage> findAllSystemMessageInASC();

    List<SystemMessage> findAllSystemMessageInDESC();

    List<SystemMessage> findSystemMessageByUserAccountUsername(String name);

    List<SystemMessage> findSystemMessageByContent(String content);

    List<SystemMessage> findSystemMessageByDay(String day);

    List<SystemMessage> findSystemMessageByMessageStatus(int info);

    SystemMessage findSystemMessageByMessageId(int id);

    boolean insertSystemMessage(SystemMessage systemMessage);

    boolean deleteSystemMessageByMessageId(int id);

    boolean deleteSystemMessageByUserAccountUsername(String userAccountUsername);

    boolean deleteSystemMessageByDay(String day);
}
