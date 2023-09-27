package com.cy.store.service;

import com.cy.store.entity.User;

public interface IUserService {

    Integer changePassword(String oldPassword, String newPassword, String username);

    /**
     * 用户登录
     * @param user
     */
    User login(String username,String password);

    /**
     * 用户注册
     * @param user
     */
    void register(User user);

}
