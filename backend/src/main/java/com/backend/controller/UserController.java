package com.backend.controller;


import com.backend.entity.RestBean;
import com.backend.entity.auth.Admin;
import com.backend.entity.user.AccountUser;
import com.backend.service.AuthorizeService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    AuthorizeService service;


    @GetMapping("/me")
    public RestBean<AccountUser> me(@SessionAttribute("account") AccountUser user){
        System.out.println(RestBean.success());
        return RestBean.success(user);
    }

    @GetMapping("/findAllAccount")
    public RestBean<Admin> findAllAccount(@SessionAttribute("account") AccountUser user){
        Admin account = service.findAllAccount(user.getUsername());
        System.out.println(account);
        if (account!=null)
            return RestBean.success(account);
        else
            return RestBean.failure(400,null);
    }






}
