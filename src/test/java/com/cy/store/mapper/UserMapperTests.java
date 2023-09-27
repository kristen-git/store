package com.cy.store.mapper;

import com.cy.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: zhuangxin
 * @CreateTime: 2023-02-15  18:17
 * @Description: TODO
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert(){
        User user = new User();
        user.setUsername("zx");
        user.setGender(0);
        user.setPassword("123456");
        Integer rows = userMapper.insert(user);
        System.out.println(rows);
    }
    @Test
    public void findByUsername(){
        String username="zx";
        User byUsername = userMapper.findByUsername(username);
        System.out.println(byUsername);
    }
}
