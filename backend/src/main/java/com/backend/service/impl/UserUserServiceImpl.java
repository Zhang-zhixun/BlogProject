package com.backend.service.impl;

import com.backend.entity.PageResult;
import com.backend.entity.User;
import com.backend.mapper.UserUserMapper;
import com.backend.service.UserUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserUserServiceImpl implements UserUserService {

    @Autowired
    UserUserMapper userMapper;

    @Override
    public List<User> findByAllUser() {
        return userMapper.findByAllUser();
    }

    @Override
    public PageResult findByAllUserPaging(Integer page, Integer size) {
        List<User> sites = userMapper.findByAllUser(); //获取全部信息
        int currentPage = page;    //当前页码
        int pageSize = size;       //每页显示多少条

        System.out.println(sites);
        int total = sites.size();//拿到总长度

        int baoliuchangduqidian = (currentPage - 1) * pageSize;        //切割起点
        int baoliuchangduzhongdian = baoliuchangduqidian + pageSize - 1; //切割终点
        int remaider = total % size;  //(先计算出余数)                    //确认最后一页是否不足（向上取整）
        int pagemax = 0;//最大页数（有多少页）
        if (remaider >= 1) {
            pagemax = (total / pageSize) + 1;
        } else {
            pagemax = total / pageSize;
        }

        List<User> sitesbaoliu = new ArrayList<User>();

        for (int i = baoliuchangduqidian; i <= baoliuchangduzhongdian; i++) {  //会越界
            if (i >= sites.size()) {
                continue;
            } else {
                sitesbaoliu.add(sites.get(i));
            }
        }

        System.out.println();
        System.out.println("sitesbaoliu ==>");
        for (User user : sitesbaoliu) {
            System.out.println(user);
        }
        System.out.print("total ==>");
        System.out.println(total);

        System.out.print("page ==>");
        System.out.println(page);
        System.out.print("size ==>");
        System.out.println(size);
        System.out.print("currentPage ==>");
        System.out.println(currentPage);
        System.out.print("pageSize ==>");
        System.out.println(pageSize);

        System.out.print("baoliuchangduqidian ==>");
        System.out.println(baoliuchangduqidian);

        System.out.print("baoliuchangduzhongdian ==>");
        System.out.println(baoliuchangduzhongdian);
        System.out.print("remaider ==>");
        System.out.println(remaider);
        System.out.print("pagemax ==>");
        System.out.println(pagemax);

        PageResult pageResult = new PageResult(sitesbaoliu,total);
        return pageResult;
    }

    @Override
    public int DelectUserId(int id) {
        return userMapper.DelectUserId(id);
    }

    @Override
    public User SelectOnefindByUserId(int id) {
        return userMapper.SelectOnefindByUserId(id);
    }

    @Override
    public int UpdateUserOneByUserId(User user) {
        return userMapper.UpdateUserOneByUserId(user);
    }

    @Override
    public List<User> SelectNameLinkFindByUser(String name) {
        return userMapper.SelectNameLinkFindByUser(name);
    }

}
