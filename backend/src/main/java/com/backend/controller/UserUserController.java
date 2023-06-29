package com.backend.controller;

import com.backend.entity.*;
import com.backend.entity.tools.RestBean;
import com.backend.service.UserUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserUserController {

    @Autowired
    UserUserService userUserService;

    @GetMapping("/findByAllUser")
        //找全部
    RestBean<PageResult> findByAllUser() {//获取全部直接变json（改，增加分页）
        List<User> list = userUserService.findByAllUser();
        PageResult pageResult = new PageResult(list, list.size());
        return RestBean.success(pageResult);
    }


    @PostMapping("/findByAllUser")
        //测试通
    void findByAllUser2(@RequestParam("page") String page,
                        @RequestParam("size") String size) {//获取全部直接变json（改，增加分页）
        System.out.println("findByAllUser2 ==>");
        System.out.println("page =");
        System.out.println(page);

        System.out.println("size =");
        System.out.println(size);
    }


    @GetMapping("/deleteUserId/{id}")
    RestBean<String> deleteUserId(@PathVariable("id") int id) {
        if (userUserService.DelectUserId(id) >= 1) {
            return RestBean.success("删除成功");
        } else {
            return RestBean.failure(404, "删除失败");
        }
    }

    @GetMapping("/findByOneUser/{id}")
        //根据id编辑对应的用户信息
    RestBean<User> findByOneUser(@PathVariable("id") int id) {//获取全部直接变json（改，增加分页）
        User user = userUserService.SelectOnefindByUserId(id);
        return RestBean.success(user);
    }

    @PostMapping("/UpdateUserOneByUserId")
        //详情提交
    RestBean<String> UpdateUserOneByUserId(@RequestParam("userId") int userId,
                                           @RequestParam("name") String name,
                                           @RequestParam("gender") String gender,
                                           @RequestParam("age") int age,
                                           @RequestParam("school") String school,
                                           @RequestParam("email") String email,

                                           @RequestParam("identity") String identity,
                                           @RequestParam("password") String password,
            /*  @RequestParam("userAccount") UserAccount userAccount 无法传递，传递参数扔进去就行  */
                                           @RequestParam("userAccountUsername") String userAccountUsername
    ) {


        System.out.println("UpdateUserOneByUserId2 ==>");
        System.out.println();

        System.out.print("userId = ");
        System.out.println(userId);

        System.out.print("name = ");
        System.out.println(name);

        System.out.print("gender = ");
        System.out.println(gender);

        System.out.print("age = ");
        System.out.println(age);

        System.out.print("school = ");
        System.out.println(school);

        System.out.print("email = ");
        System.out.println(email);

        System.out.print("identity = ");
        System.out.println(identity);

        System.out.print("userAccountUsername = ");
        System.out.println(userAccountUsername);


        System.out.print("password = ");
        System.out.println(password);

        System.out.println("UpdateUserOneByUserId  ==> ");
        System.out.println("=============6-begin=============");


        User user1 = new User();
        user1.setUserId(userId);
        user1.setName(name);
        user1.setGender(gender);
        user1.setAge(age);
        user1.setSchool(school);
        user1.setIdentity(identity);
        UserAccount userAccount = new UserAccount();
        userAccount.setUserAccountUsername(userAccountUsername);
        userAccount.setPassword(password);
        userAccount.setEmail(email);
        user1.setUserAccount(userAccount);

        //UpdateUserOneByUserId

        int i = userUserService.UpdateUserOneByUserId(user1);

        if (i >= 1) {
            System.out.println("tiaozhuang");
            return RestBean.success("添加成功");
        } else {
            return RestBean.failure(404, "添加失败");
        }


    }

    static String finalName = "";
    static boolean flag = false;
static List<User> huangcun=new ArrayList<>();

    @PostMapping("/SelectNameLinkFindByUser")
        //找人，先提交名字
    void SelectNameLinkFindByUser(@RequestParam("InputSearch") String InputSearch
    ) {//获取全部直接变json（改，增加分页）
        System.out.println("=================SelectNameLinkFindByUser======begin==========================");
        finalName = "";//字符串清空


        System.out.println("findByAllUser2 ==>");
        System.out.println("pages =");
        System.out.println(InputSearch);
        finalName = finalName + InputSearch;
        flag = true;

        System.out.println("finalName =");
        System.out.println(finalName);

        //要搜索的放进来了，把值搜一下存成List<User>
        List<User> users = userUserService.SelectNameLinkFindByUser(finalName);
        System.out.print("users ==>");
        System.out.println(users);

        huangcun.clear();
        for (User user : users) {
            huangcun.add(user);
        }
        System.out.print("huangcun ==>");
        System.out.println(huangcun);


        System.out.println("=================SelectNameLinkFindByUser======end==========================");
    }


    @GetMapping("/SelectNameLinkFindByUser2")
        //找人，名字提交了，去找人搜索，清除名字
    RestBean<List<User>> SelectNameLinkFindByUser2() {//获取全部直接变json（改，增加分页）
        System.out.println("=================SelectNameLinkFindByUser2======begin==========================");
        System.out.println("finalName ==>");
        System.out.println(finalName);

        if (flag) {
            List<User> list = userUserService.SelectNameLinkFindByUser(finalName);
            flag = false;
            for (User user : list) {
                System.out.println(user);
            }

            System.out.println("=========true========SelectNameLinkFindByUser2======end==========================");
            return RestBean.success(list);
        } else
            System.out.println("==========false=======SelectNameLinkFindByUser2======end==========================");
        return null;
    }


    static int currentPage4 = 0;
    static int pageSize = 0;
    static PageResult qiege =new PageResult(null,0);




    @PostMapping("/handleCurrentChangePost")
        //页面跳转
    void handleCurrentChangePost(
            @RequestParam("currentPage4reactive") int currentPage4reactive,
            @RequestParam("pageSizereactive") int pageSizereactive
    ) {//对接触发handleCurrentChange  传进来数据保存下次直接读取         currentPage4 是当前页面的值
        System.out.println("=================SelectNameLinkFindByUser======begin==========================");

        currentPage4 = 0;//清空
        pageSize = 0;

        System.out.print("currentPage4reactive ==>");
        System.out.println(currentPage4reactive);

        System.out.print("pageSizereactive ==>");
        System.out.println(pageSizereactive);

        currentPage4 = currentPage4reactive;
        pageSize = pageSizereactive;

        PageResult qiege = qiege(currentPage4, pageSize);


        System.out.println("=================SelectNameLinkFindByUser======end==========================");
    }


    @GetMapping("/handleCurrentChangePost2")
        //页面跳转
    RestBean<PageResult> handleCurrentChangePost() {//获取全部直接变json（改，增加分页）
        System.out.println("=================handleCurrentChangePost======begin==========================");

        System.out.print("currentPage4 ==>");
        System.out.println(currentPage4);

        System.out.print("pageSize ==>");
        System.out.println(pageSize);


        PageResult byAllUserPaging = userUserService.findByAllUserPaging(currentPage4, pageSize);

        System.out.println("=================handleCurrentChangePost======end==========================");

        return RestBean.success(byAllUserPaging);
    }





    public PageResult qiege(Integer page, Integer size){
        List<User> sites = huangcun;          //获取全部信息(待切肉)
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

}
