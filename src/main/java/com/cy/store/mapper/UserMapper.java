package com.cy.store.mapper;

import com.cy.store.entity.User;

public interface UserMapper {

    /**
     * 添加用户
     * @param user 用户数据
     * @return 影响的行数
     */
    Integer insert(User user);

    /**
     * 根据用户名查询用户数据
     * @param username 用户名
     * @return 匹配用户数据，没有的话返回null
     */
    User findByUsername(String username);
    /**
     * 根据用户名修改密码
     * @param username 用户名
     * @return 无返回值，修改成功即可
     */
    Void changePassword();
}
