package com.cy.store.service;

import com.cy.store.entity.User;
import com.cy.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: zhuangxin
 * @CreateTime: 2023-02-20  21:47
 * @Description: TODO
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {
    @Autowired
    private IUserService iUserService;

    @Test
    public void register() {
        try {
            User user = new User();
            user.setUsername("lower");
            user.setPassword("123456");
            user.setGender(1);
            user.setPhone("17858802974");
            user.setEmail("lower@tedu.cn");
            user.setAvatar("xxxx");
            iUserService.register(user);
            System.out.println("注册成功！");
        } catch (ServiceException e) {
            System.out.println("注册失败！" + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}
