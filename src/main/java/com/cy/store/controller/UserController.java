package com.cy.store.controller;

import com.cy.store.entity.User;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.UsernameDuplicateException;
import com.cy.store.service.impl.UserServiceImpl;
import com.cy.store.util.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author: zhuangxin
 * @CreateTime: 2023-02-21  21:40
 * @Description: TODO
 */
@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private RedisTemplate redisTemplate;
    @RequestMapping("register")
    public ResultJson<Void> register(User user) {
        // 创建返回值
        ResultJson<Void> resultJson = new ResultJson<>();
        try {
            userService.register(user);
            resultJson.setCode(200);
            resultJson.setMessage("注册成功");
        } catch (UsernameDuplicateException e) {
            resultJson.setCode(400);
            resultJson.setMessage(user.getUsername() + "已注册,请重新注册");
        } catch (InsertException e) {
            resultJson.setCode(500);
            resultJson.setMessage(user.getUsername() + "注册失败，请联系系统管理员");
        }
        return resultJson;
    }

    @RequestMapping("login")
    public ResultJson<User> login(String username, String password) {
        // 调用业务对象的方法执行登录，并获取返回值
        User data = userService.login(username, password);
        // 将以上返回值和状态码OK封装到响应结果中并返回
        return new ResultJson<User>(200, data);
        // 调用业务对象的方法执行登录，并获取返回值
    }
    @RequestMapping("change_password")
    public ResultJson<Void> changePassword(String oldPassword,
                                           String newPassword,
                                           String username,
                                           HttpServletResponse response,
                                           HttpServletRequest request){
        Integer i = userService.changePassword(oldPassword, newPassword, username);
        String token = request.getHeader("token");
        ResultJson<Object> resultJson = new ResultJson<>();
        if(i==1){
            resultJson.setCode(200);
            redisTemplate.delete(token);
        }else{
            resultJson.setCode(400);
        }


        return null;
    }
}
