package com.backend.controller;

import com.backend.entity.SystemMessage;
import com.backend.entity.Teacher;
import com.backend.entity.UserAccount;
import com.backend.entity.tools.RestBean;
import com.backend.service.SystemMessageService;
import com.backend.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/index")
public class SystemMessageController {

    @Autowired
    SystemMessageService systemMessageService;

    @Autowired
    UserAccountService userAccountService;

    @GetMapping("/getAllSystemMessageInASC")
    public RestBean<List<SystemMessage>> findAllSystemMessageInASC() {
        return RestBean.success(systemMessageService.findAllSystemMessageInASC());
    }

    @GetMapping("/getAllSystemMessageInDESC")
    public RestBean<List<SystemMessage>> findAllSystemMessageInDESC() {
        return RestBean.success(systemMessageService.findAllSystemMessageInDESC());
    }

    @PostMapping("/getSystemMessageByUserAccountUsername")
    public RestBean<List<SystemMessage>> findSystemMessageByUserAccountUsername(@RequestParam("name") String name) {
        return RestBean.success(systemMessageService.findSystemMessageByUserAccountUsername(name));
    }

    @PostMapping("/getSystemMessageByContent")
    public RestBean<List<SystemMessage>> findSystemMessageByContent(@RequestParam("content") String content) {
        return RestBean.success(systemMessageService.findSystemMessageByContent(content));
    }

    @PostMapping("/getSystemMessageByDay")
    public RestBean<List<SystemMessage>> findSystemMessageByDay(@RequestParam("day") String day) {
        return RestBean.success(systemMessageService.findSystemMessageByDay(day));
    }

    @GetMapping("/getSystemMessageByMessageStatus/{info}")
    public RestBean<List<SystemMessage>> findSystemMessageByMessageStatus(@PathVariable int info) {
        return RestBean.success(systemMessageService.findSystemMessageByMessageStatus(info));
    }

    @GetMapping("/getSystemMessageByMessageId/{id}")
    public RestBean<SystemMessage> findSystemMessageByMessageId(@PathVariable int id) {
        return RestBean.success(systemMessageService.findSystemMessageByMessageId(id));
    }

    @GetMapping("/getByAlluserAccountUsernameAccount")
    public RestBean<List<UserAccount>> findByAlluserAccountUsernameAccount() {
        return RestBean.success(userAccountService.findByAlluser_account_usernameAccount());
    }

    @PostMapping("/insertSystemMessage")
    public RestBean<String> insertSystemMessage(@RequestParam("content") String content,
                                                       @RequestParam("sendTime") String sendTime,
                                                       @RequestParam("userAccountUsername") String userAccountUsername) {
        boolean flag = false;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            Date date = dateFormat.parse(sendTime);
            Timestamp timestamp = new Timestamp(date.getTime());
            userAccountUsername = userAccountUsername.replace("[", "");
            userAccountUsername = userAccountUsername.replace("]", "");
            userAccountUsername = userAccountUsername.replace("\"", "");
            String[] strs = userAccountUsername.split(",");

            for (int i = 0; i < strs.length; i++) {
                UserAccount userAccount = new UserAccount();
                userAccount.setUserAccountUsername(strs[i]);
                SystemMessage systemMessage = new SystemMessage();
                systemMessage.setContent(content);
                systemMessage.setSendTime(timestamp);
                systemMessage.setUserAccount(userAccount);
                flag = systemMessageService.insertSystemMessage(systemMessage);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(flag){
            return RestBean.success("发送成功！");
        }else{
            return RestBean.failure(404,"发送失败！");
        }
    }

    @GetMapping("/deleteSystemMessageByMessageId/{id}")
    public RestBean<String> deleteSystemMessageByMessageId(@PathVariable int id) {
        if(systemMessageService.deleteSystemMessageByMessageId(id)){
            return RestBean.success("删除成功！");
        }else{
            return RestBean.failure(404,"删除失败！");
        }
    }

    @PostMapping("/deleteSystemMessageByUserAccountUsername")
    public RestBean<String> deleteSystemMessageByUserAccountUsername(@RequestParam("userAccountUsername") String userAccountUsername) {
        if(systemMessageService.deleteSystemMessageByUserAccountUsername(userAccountUsername)){
            return RestBean.success("删除成功！");
        }else{
            return RestBean.failure(404,"删除失败！");
        }
    }

    @PostMapping("/deleteSystemMessageByDay")
    public RestBean<String> deleteSystemMessageByDay(@RequestParam("day") String day) {
        if(systemMessageService.deleteSystemMessageByDay(day)){
            return RestBean.success("删除成功！");
        }else{
            return RestBean.failure(404,"删除失败！");
        }
    }

}
