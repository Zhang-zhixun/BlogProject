package com.backend.service.impl;

import com.backend.entity.auth.AdminAccount;
import com.backend.entity.auth.Admin;
import com.backend.mapper.UserMapper;
import com.backend.service.AuthorizeService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthorizeServiceImpl implements AuthorizeService {

    @Value("${spring.mail.username}")
    String from;

    @Resource
    UserMapper mapper;

    @Resource
    MailSender mailSender;

    //加密
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String email_code;

//    @Resource
//    StringRedisTemplate redisTemplate;

    //登录
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AdminAccount adminAccount = mapper.findByUsernameOrEmail(username);
        if (username == null) throw new UsernameNotFoundException("用户名不能为空");
            if (adminAccount == null) {
                throw new UsernameNotFoundException("用户名或密码错误");
            }else{
                return User
                        .withUsername(adminAccount.getAdminUsername())
                        .password(adminAccount.getPassword())
                        .roles("admin")
                        .build();
            }
        }


    //管理员注册
    @Override
    public String registerAccount(AdminAccount adminAccount, String code) {
        if ((mapper.findByUsernameOrEmail(adminAccount.getAdminUsername()) != null))
            return "用户名已存在";
        if (code == null)
            return "请先发送获取验证码";
        if (code.equals(email_code)) {
            //密码加密
            String password = encoder.encode(adminAccount.getPassword());
            adminAccount.setPassword(password);
            if (mapper.registerAccount(adminAccount) > 0) {
                email_code = "";
                return null;
            } else
                return "内部错误,请联系管理员";
        } else {
            return "验证码错误，请重新输入";
        }
    }


    //重置密码
    @Override
    public boolean resetPassword(String password, String email) {
        //加密
        password = encoder.encode(password);
        return mapper.resetPasswordByEmail(password, email) > 0 ? true : false;
    }

    @Override
    public Admin findAllAccount(String username) {
        return mapper.findAllAccount(username);
    }

    @Override
    public boolean updateAccount(Admin admin) {
        return mapper.updateAccount(admin);
    }

    //重置密码 邮件验证码验证
    @Override
    public String validateOnly(String email, String code, String sessionId) {
        if ((mapper.findByUsernameOrEmail(email) == null))
            return "邮箱不存在";
        if (code == null)
            return "请先发送获取验证码";
        if (code.equals(email_code)) {
            email_code = "";
            return null;
        } else
            return "验证码错误，请重新输入";
    }


    /**
     * 1. 生成验证码
     * 2. 将验证码放入redis 时间3分钟 验证码存入低于两分钟可重新发送
     * 3. 发送验证码到指定邮箱
     * 4. 若发送失败，把redis里的验证码删除
     * 5. 若成功，用户注册时从redis取出键值对，然后验证验证码是否一直
     */
    @Override
    public String sendValidateEmail(String email, String sessionId, String hasAccount) {
//        String key = "email:" + sessionId + ":" + email + ":" + hasAccount;
        //判断验证码是否低于两分钟
//        if (Boolean.TRUE.equals(stringRedisTemplate.hasKey(key))) {
//            Long expire = Optional.ofNullable(stringRedisTemplate.getExpire(key, TimeUnit.SECONDS)).orElse(0L);
//            if (expire > 120) {
//                return false;
//            }
//        }
        AdminAccount adminAccount = mapper.findByUsernameOrEmail(email);
        System.out.println(adminAccount);

        if (adminAccount == null && hasAccount.equals("reset")){
            System.out.println("没有此邮件的用户");
            return "没有此邮件的用户";}
        if (adminAccount != null && hasAccount.equals("register")){

            return "此邮件的已被注册";}
        Random random = new Random();
        //生成6位数的验证码
        email_code = (random.nextInt(899999) + 100000) + "";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        //发给谁
        message.setTo(email);
        message.setSubject("您的验证邮件");
        message.setText("😎惠健康医疗平台 | 验证码😎 ：" + email_code);
        System.out.println(message);
        //发送失败 捕获异常
        try {
            mailSender.send(message);
            //存入redis 验证码只能存3分钟
            //stringRedisTemplate.opsForValue().set(key, String.valueOf(code), 3, TimeUnit.MINUTES);
            //System.out.println(stringRedisTemplate+"1231231");
            return null;
        } catch (MailException e) {
            e.printStackTrace();
            return "邮件发送失败，请检查邮件地址是否有效";
        }
    }
}

//    @Override
//    public String sendValidateEmail(String email, String sessionId, String hasAccount) {
////        String key = "email:" + sessionId + ":" + email + ":" + hasAccount;
//        //判断验证码是否低于两分钟
////        if (Boolean.TRUE.equals(stringRedisTemplate.hasKey(key))) {
////            Long expire = Optional.ofNullable(stringRedisTemplate.getExpire(key, TimeUnit.SECONDS)).orElse(0L);
////            if (expire > 120) {
////                return false;
////            }
////        }
//        Account account = m.findCustomersAccountByCaEmail(email);
//        if (cAccount == null && hasAccount.equals("reset")){
//            System.out.println("没有此邮件的用户");
//            return "没有此邮件的用户";}
//        if (cAccount != null && hasAccount.equals("register")){
//            return "此邮件的已被注册";}
//        Random random = new Random();
//        //生成6位数的验证码
//        email_code = (random.nextInt(899999) + 100000) + "";
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(from);
//        //发给前端el-input输入的邮箱
//        message.setTo(email);
//        message.setSubject("您的验证邮件");
//        message.setText("😎惠健康医疗平台 | 验证码😎 ：" + email_code);
//        //发送失败 捕获异常
//        try {
//            mailSender.send(message);
//            //存入redis 验证码只能存3分钟
//            //stringRedisTemplate.opsForValue().set(key, String.valueOf(code), 3, TimeUnit.MINUTES);
//            //System.out.println(stringRedisTemplate+"1231231");
//            return null;
//        } catch (MailException e) {
//            e.printStackTrace();
//            return "邮件发送失败，请检查邮件地址是否有效";
//        }
//    }
//}
