package com.backend.controller;

import com.backend.entity.UserMessage;
import com.backend.entity.tools.RestBean;
import com.backend.service.UserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/index")
public class UserMessageController {


    @Autowired
    UserMessageService userMessageService;

    @GetMapping("/getAllUserMessageInASC")
    public List<UserMessage> findAllUserMessageInASC() {
        return userMessageService.findAllUserMessageInASC();
    }

    @GetMapping("/getAllUserMessageInDESC")
    public List<UserMessage> findAllUserMessageInDESC() {
        return userMessageService.findAllUserMessageInDESC();
    }


    @PostMapping("/getUserMessageBySenderName")
    public List<UserMessage> findUserMessageBySenderName(@RequestParam("name") String name) {
        return userMessageService.findUserMessageBySenderName(name);
    }

    @PostMapping("/getUserMessageByReceiverName")
    public List<UserMessage> findUserMessageByReceiverName(@RequestParam("name") String name) {
        return userMessageService.findUserMessageByReceiverName(name);
    }

    @PostMapping("/getUserMessageByContent")
    public List<UserMessage> findUserMessageByContent(@RequestParam("content") String content) {
        return userMessageService.findUserMessageByContent(content);
    }

    @PostMapping("/getUserMessageByDay")
    public List<UserMessage> findUserMessageByDay(@RequestParam("day") String day) {
        return userMessageService.findUserMessageByDay(day);
    }

    @GetMapping("/getUserMessageByMessageStatus/{info}")
    public List<UserMessage> findUserMessageByMessageStatus(@PathVariable int info) {
        return userMessageService.findUserMessageByMessageStatus(info);
    }

    @GetMapping("/getUserMessageByMessageId/{id}")
    public UserMessage findUserMessageByMessageId(@PathVariable int id) {
        return userMessageService.findUserMessageByMessageId(id);
    }

    @GetMapping("/deleteUserMessageByMessageId/{id}")
    public RestBean<String> deleteUserMessageByMessageId(@PathVariable int id) {
        if(userMessageService.deleteUserMessageByMessageId(id)){
            return RestBean.success("删除成功！");
        }else{
            return RestBean.failure(404,"删除成功！");
        }
    }

    @GetMapping("/deleteUserMessageBySenderId/{id}")
    public RestBean<String> deleteUserMessageBySenderId(@PathVariable int id) {
        if(userMessageService.deleteUserMessageBySenderId(id)){
            return RestBean.success("删除成功！");
        }else{
            return RestBean.failure(404,"删除成功！");
        }
    }

    @GetMapping("/deleteUserMessageByReceiverId/{id}")
    public RestBean<String> deleteUserMessageByReceiverId(@PathVariable int id) {
        if(userMessageService.deleteUserMessageByReceiverId(id)){
            return RestBean.success("删除成功！");
        }else{
            return RestBean.failure(404,"删除失败！");
        }
    }
}
