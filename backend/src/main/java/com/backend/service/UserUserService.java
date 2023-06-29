package com.backend.service;

import com.backend.entity.PageResult;
import com.backend.entity.User;

import java.util.List;

public interface UserUserService {

    List<User> findByAllUser();

    PageResult findByAllUserPaging(Integer page, Integer size);//要分页
    int DelectUserId(int id);//删除

    User SelectOnefindByUserId(int id);//找目标用户
    int UpdateUserOneByUserId(User user);//根据id修改用户信息

    List<User> SelectNameLinkFindByUser(String name);//按照名字模糊查询 要分页

}
