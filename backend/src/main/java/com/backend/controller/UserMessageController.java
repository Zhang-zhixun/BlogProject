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
    public RestBean<List<UserMessage>> findAllUserMessageInASC() {
        return RestBean.success(userMessageService.findAllUserMessageInASC());
    }

    @GetMapping("/getAllUserMessageInDESC")
    public RestBean<List<UserMessage>> findAllUserMessageInDESC() {
        return RestBean.success(userMessageService.findAllUserMessageInDESC());
    }


    @PostMapping("/getUserMessageBySenderName")
    public RestBean<List<UserMessage>> findUserMessageBySenderName(@RequestParam("name") String name) {
        return RestBean.success(userMessageService.findUserMessageBySenderName(name));
    }

    @PostMapping("/getUserMessageByReceiverName")
    public RestBean<List<UserMessage>> findUserMessageByReceiverName(@RequestParam("name") String name) {
        return RestBean.success(userMessageService.findUserMessageByReceiverName(name));
    }

    @PostMapping("/getUserMessageByContent")
    public RestBean<List<UserMessage>> findUserMessageByContent(@RequestParam("content") String content) {
        return RestBean.success(userMessageService.findUserMessageByContent(content));
    }

    @PostMapping("/getUserMessageByDay")
    public RestBean<List<UserMessage>> findUserMessageByDay(@RequestParam("day") String day) {
        return RestBean.success(userMessageService.findUserMessageByDay(day));
    }

    @GetMapping("/getUserMessageByMessageStatus/{info}")
    public RestBean<List<UserMessage>> findUserMessageByMessageStatus(@PathVariable int info) {
        return RestBean.success(userMessageService.findUserMessageByMessageStatus(info));
    }

    @GetMapping("/getUserMessageByMessageId/{id}")
    public RestBean<UserMessage> findUserMessageByMessageId(@PathVariable int id) {
        return RestBean.success(userMessageService.findUserMessageByMessageId(id));
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

    @GetMapping("/getUserMessageBySenderIdOrReceiverId/{id}")
    public RestBean<List<UserMessage>> findUserMessageBySenderIdOrReceiverId(@PathVariable int id) {
        return RestBean.success(userMessageService.findUserMessageBySenderIdOrReceiverId(id));
    }

}
