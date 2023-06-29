package com.backend.mapper;

import com.backend.entity.UserMessage;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserMessageMapperTest {

    @Test
    void findAllUserMessageInASC() {
        UserMessageMapper userMessageMapper = new UserMessageMapper() {
            @Override
            public List<UserMessage> findAllUserMessageInASC() {
                return null;
            }

            @Override
            public List<UserMessage> findAllUserMessageInDESC() {
                return null;
            }

            @Override
            public List<UserMessage> findUserMessageBySenderName(String name) {
                return null;
            }

            @Override
            public List<UserMessage> findUserMessageByReceiverName(String name) {
                return null;
            }

            @Override
            public List<UserMessage> findUserMessageByContent(String content) {
                return null;
            }

            @Override
            public List<UserMessage> findUserMessageByDay(String day) {
                return null;
            }

            @Override
            public List<UserMessage> findUserMessageByMessageStatus(int info) {
                return null;
            }

            @Override
            public UserMessage findUserMessageByMessageId(int id) {
                return null;
            }

            @Override
            public boolean deleteUserMessageByMessageId(int id) {
                return false;
            }

            @Override
            public boolean deleteUserMessageBySenderId(int id) {
                return false;
            }

            @Override
            public boolean deleteUserMessageByReceiverId(int id) {
                return false;
            }
        };

        // 调用要测试的方法，获取返回结果
        List<UserMessage> userMessages = userMessageMapper.findAllUserMessageInASC();

        // 输出测试结果
        System.out.println("测试通过！返回结果数量：" + userMessages.size());
    }
}